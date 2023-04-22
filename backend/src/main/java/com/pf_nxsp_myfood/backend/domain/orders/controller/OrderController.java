package com.pf_nxsp_myfood.backend.domain.orders.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// SpringBoot
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// GSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// PAYPAL
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;

// LOCAL
import com.pf_nxsp_myfood.backend.domain.clients.service.ClientService;
import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;
import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.service.OrderService;
import com.pf_nxsp_myfood.backend.domain.payload.request.order.NewOrderRequest;
import com.pf_nxsp_myfood.backend.domain.products.service.ProductService;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;
import com.stripe.Stripe;
// STRIPE
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Source;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Value("${paypal.client-id}")
    private String paypal_clientId;

    @Value("${paypal.client-secret}")
    private String paypal_clientSecret;

    @Value("${stripe.api.secretKey}")
    private String stripe_secretKey;

    @Autowired
    private OrderService oService;

    @Autowired
    private ClientService cService;

    @Autowired
    private ProductService pService;

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @GetMapping
    public OrderDto.MultipleOrders getOrders(@AuthenticationPrincipal AuthClientDetails aDetails) {
        System.out.println(LocalDate.now());
        if (aDetails.getType() != EmployeesTypes.NONE || aDetails.getType() != null) {
            return OrderDto.MultipleOrders
                    .builder()
                    .orders(oService.getOrders())
                    .build();
        }

        return null;
    }

    @GetMapping("/user")
    public OrderDto.MultipleOrders getUserOrders(@AuthenticationPrincipal AuthClientDetails aDetails) {
        return OrderDto.MultipleOrders
                .builder()
                .orders(oService.getUserOrders(cService.currentUser(aDetails).getId_client()))
                .build();
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody NewOrderRequest newOrderRequest) throws StripeException {
        Stripe.apiKey = stripe_secretKey;
        OrderDto dto = new OrderDto();

        // Convertir token en objeto Source
        Map<String, Object> sourceParams = new HashMap<>();
        sourceParams.put("type", "card");
        sourceParams.put("token", newOrderRequest.getCard_token());

        Source source = Source.create(sourceParams);

        dto.setId_order(IdGenerator.generateWithLength(20));
        dto.setId_client(cService.currentUser(aDetails).getId_client());
        dto.setStatus(OrderTypes.PENDING);
        dto.setOrderDate(LocalDate.now());

        newOrderRequest.getProducts()
                .stream()
                .forEach(o -> o.setId_order(dto.getId_order()));

        BigDecimal amount = newOrderRequest.getProducts().stream()
                .map(e -> new BigDecimal(pService.getProductById(e.getId_product()).getPrice())
                        .multiply(new BigDecimal(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        amount = amount.setScale(2, RoundingMode.HALF_UP);

        Map<String, Object> paymentParams = new HashMap<>();

        int amountInCents = amount.multiply(new BigDecimal(100)).intValueExact();

        // Creates Stripe Order Payment
        paymentParams.put("amount", amountInCents);
        paymentParams.put("currency", "eur");
        paymentParams.put("description", String.format("Pago de orden #%s", dto.getId_order()));
        paymentParams.put("source", source.getId());

        PaymentIntent paymentIntent;
        Map<String, Object> res = new HashMap<>();

        try {
            paymentIntent = PaymentIntent.create(paymentParams);
            paymentIntent.confirm();
            res.put("order", oService.addOrder(dto, newOrderRequest.getProducts()));
            res.put("stripe", paymentParams.get("description"));

            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Cannot Create the Order");
        }
    }

    // FIXME: Not Working
    @PostMapping("/createOrder")
    public ResponseEntity<?> paypalCreateOrder() {
        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(System.getProperty("paypal.client-id"),
                System.getProperty("paypal.client-secret"));

        PayPalHttpClient httpClient = new PayPalHttpClient(environment);
        OrdersCreateRequest req = new OrdersCreateRequest();

        req.prefer("return=representation");
        req.requestBody(new OrderRequest().checkoutPaymentIntent("CAPTURE").purchaseUnits(
                Arrays.asList(
                        new PurchaseUnitRequest().amountWithBreakdown(
                                new AmountWithBreakdown().currencyCode("USD").value("100.00")))));

        System.out.println(paypal_clientId);
        System.out.println(paypal_clientSecret);

        try {
            HttpResponse<Order> response = httpClient.execute(req);
            Order order = response.result();
            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

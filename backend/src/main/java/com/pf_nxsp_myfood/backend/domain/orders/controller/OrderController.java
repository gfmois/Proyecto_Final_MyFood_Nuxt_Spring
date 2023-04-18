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
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

// STRIPE
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Autowired
    private OrderService oService;

    @Autowired
    private ClientService cService;

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
        OrderDto dto = new OrderDto();

        dto.setId_order(IdGenerator.generateWithLength(20));
        dto.setId_client(cService.currentUser(aDetails).getId_client());
        dto.setStatus(OrderTypes.PENDING);
        dto.setOrderDate(LocalDate.now());

        newOrderRequest.getProducts()
                .stream()
                .forEach(o -> o.setId_order(dto.getId_order()));

        BigDecimal amount = newOrderRequest.getProducts().stream()
                .map(e -> new BigDecimal(e.getProducts().getPrice())
                        .multiply(new BigDecimal(e.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        amount = amount.setScale(2, RoundingMode.HALF_UP);

        Map<String, Object> paymentParams = new HashMap<>();

        // Creates Stripe Order Payment
        paymentParams.put("amount", amount);
        paymentParams.put("currency", "eur");
        paymentParams.put("description", String.format("Pago de orden #%s", dto.getId_order()));
        paymentParams.put("source", newOrderRequest.getCard_token());

        try {
            PaymentIntent.create(paymentParams);
            return oService.addOrder(dto, newOrderRequest.getProducts());
        } catch (Exception e) {
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

        System.out.println(clientId);
        System.out.println(clientSecret);

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

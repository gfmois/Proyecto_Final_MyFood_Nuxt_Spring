package com.pf_nxsp_myfood.backend.domain.orders.controller;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pf_nxsp_myfood.backend.domain.clients.service.ClientService;
import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;
import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderProductEntity;
import com.pf_nxsp_myfood.backend.domain.orders.service.OrderService;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
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
    public ResponseEntity<?> addOrder(@AuthenticationPrincipal AuthClientDetails aDetails, @RequestBody Set<OrderProductEntity> newOrderProducts) {
        OrderDto dto = new OrderDto();

        dto.setId_order(IdGenerator.generateWithLength(20));
        dto.setId_client(cService.currentUser(aDetails).getId_client());
        dto.setStatus(OrderTypes.PENDING);
        dto.setOrderDate(LocalDate.now());

        newOrderProducts
            .stream()
            .forEach(o -> o.setId_order(dto.getId_order()));

        return oService.addOrder(dto, newOrderProducts);
    }
}

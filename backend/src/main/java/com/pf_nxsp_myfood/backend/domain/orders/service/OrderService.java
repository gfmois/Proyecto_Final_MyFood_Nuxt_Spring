package com.pf_nxsp_myfood.backend.domain.orders.service;

import org.springframework.http.ResponseEntity;

import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderProductEntity;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<OrderDto> getUserOrders(String client);
    List<OrderDto> getOrders();
    ResponseEntity<?> addOrder(OrderDto order,Set<OrderProductEntity> orderProducts);
}

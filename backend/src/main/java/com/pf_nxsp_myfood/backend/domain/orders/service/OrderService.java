package com.pf_nxsp_myfood.backend.domain.orders.service;

import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderProductEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {
    List<OrderDto> getUserOrders(String client);
    List<OrderDto> getOrders();
    Map<String, Object> addOrder(OrderDto order,Set<OrderProductEntity> orderProducts);
}

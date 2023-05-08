package com.pf_nxsp_myfood.backend.domain.orders.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderEntity;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderProductEntity;
import com.pf_nxsp_myfood.backend.domain.orders.repository.OrderRepository;
import com.pf_nxsp_myfood.backend.domain.products.service.ProductService;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;
import com.pf_nxsp_myfood.backend.domain.restaurants.service.RestaurantSerivce;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    private ProductService pService;

    @Autowired
    private RestaurantSerivce rSerivce;

    private HashMap<String, ?> formatProduct(OrderProductEntity entity) {
        HashMap<String, ?> map = new HashMap<>() {
            {
                put("quantity", entity.getQuantity());
                put("product", pService.getProductById(entity.getId_product()));
            }
        };

        return map;
    }

    private OrderEntity convertDtoToEntity(OrderDto dto) {
        return OrderEntity.builder()
                .id_order(dto.getId_order())
                .id_client(dto.getId_client())
                .orderDate(dto.getOrderDate())
                .status(dto.getStatus())
                .restaurant(RestaurantEntity.builder().id_restaurant(dto.getId_restaurant()).build())
                .build();
    }

    private OrderDto converToDto(OrderEntity entity) {
        return OrderDto.builder()
                .id_client(entity.getId_client())
                .id_order(entity.getId_order())
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .id_restaurant(entity.getRestaurant_orders().getId_restaurant())
                .product_ordered(entity.getProducts_ordered()
                        .stream()
                        .map(o -> formatProduct(o))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(this::converToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<OrderDto> getUserOrders(String client) {
        return this.orderRepository
                .getOrders(client)
                .stream()
                .map(this::converToDto)
                .toList();
    }

    @Transactional
    @Override
    public Map<String, Object> addOrder(OrderDto newOrder, Set<OrderProductEntity> newOrderProducts) {
        Map<String, Object> response = new HashMap<>();
        OrderEntity newOrderEntity = convertDtoToEntity(newOrder);
        newOrderEntity.setProducts_ordered(newOrderProducts);
        newOrderEntity.setOrderDate(LocalDate.now());

        if (this.orderRepository.save(newOrderEntity) != null) {
            response.put("id_order", newOrder.getId_order());
            response.put("status", "saved");
        } else {
            response.put("id_order", "");
            response.put("status", "error");
        }

        return response;
    }

    @Override
    public List<OrderDto> getRestaurantOrdersByEmployee(String id_employee) {
        String idRestaurant = rSerivce.getRestaurantByEmployee(id_employee).getId_restaurant();
        List<OrderDto> orders = orderRepository.findByIdRestaurant(idRestaurant)
                .stream()
                .map(this::converToDto)
                .collect(Collectors.toList());

        return orders;
    }

    @Override
    public OrderDto getOrder(String id_order) {
        return converToDto(orderRepository.findById(id_order).get());
    }

    @Override
    public Map<String, Object> updateOrder(OrderDto order) {
        try {
            OrderEntity orderToUpdate = orderRepository.findById(order.getId_order()).get();
            Map<String, Object> res = new HashMap<String, Object>();

            orderToUpdate.setStatus(order.getStatus());

            if (orderRepository.save(orderToUpdate) != null) {
                OrderDto orderUpdated = converToDto(orderRepository.findById(orderToUpdate.getId_order()).get());
                res.put("status", 200);
                res.put("message", "Order updated");
                res.put("order", orderUpdated);

                return res;
            }

            res.put("status", 400);
            res.put("message", "Error trying to update the order");

            return res;
        } catch (Exception e) {
            Map<String, Object> res = new HashMap<String, Object>();

            res.put("status", 200);
            res.put("message", e.getMessage());

            return res;
        }

    }

    @Override
    public List<Map<String, Object>> getClientOrders(String id_client) {
        List<OrderDto> clientOrders = orderRepository.findAll().stream().filter(e -> e.getId_client().equals(id_client))
                .map(this::converToDto).collect(Collectors.toList());

        List<Map<String, Object>> ordersFiltered = clientOrders.stream().map(e -> {
            Map<String, Object> item = new HashMap<String, Object>();

            item.put("id_client", e.getId_client());
            item.put("id_order", e.getId_order());
            item.put("restaurant", ((RestaurantDto) rSerivce.getRestaurantById(e.getId_restaurant()).get("restaurant")).getName());
            item.put("order_date", e.getOrderDate());
            item.put("status", e.getStatus());
            item.put("products", e.getProduct_ordered());

            return item;
        }).collect(Collectors.toList());

        return ordersFiltered;
    }

}

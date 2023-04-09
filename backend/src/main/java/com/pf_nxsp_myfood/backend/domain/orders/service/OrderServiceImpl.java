package com.pf_nxsp_myfood.backend.domain.orders.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pf_nxsp_myfood.backend.domain.orders.dto.OrderDto;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderEntity;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderProductEntity;
import com.pf_nxsp_myfood.backend.domain.orders.repository.OrderRepository;
import com.pf_nxsp_myfood.backend.domain.products.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    
    @Autowired
    private ProductService pService;

    private HashMap<String, ?> formatProduct(OrderProductEntity entity) {        
        HashMap<String, ?> map = new HashMap<>(){{
            put("quantity", entity.getQuantity());
            put("product", pService.getProductById(entity.getId_product()));
        }};
        
        return map;
    }

    private OrderEntity convertDtoToEntity(OrderDto dto) {
        return OrderEntity.builder()
                .id_order(dto.getId_order())
                .id_client(dto.getId_client())
                .orderDate(dto.getOrderDate())
                .status(dto.getStatus())
                .build();
    }
    
    private OrderDto converToDto(OrderEntity entity) {
        return OrderDto.builder()
                .id_client(entity.getId_client())
                .id_order(entity.getId_order())
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
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
    public ResponseEntity<?> addOrder(OrderDto newOrder, Set<OrderProductEntity> newOrderProducts) {
        OrderEntity newOrderEntity = convertDtoToEntity(newOrder);
        newOrderEntity.setProducts_ordered(newOrderProducts);

        if(this.orderRepository.save(newOrderEntity) != null) {
            return new ResponseEntity<>(newOrder.getId_order(), HttpStatus.OK);
        }
        return new ResponseEntity<>("{'msg':'Hubo un error al crear el pedido'}", HttpStatus.BAD_REQUEST);
    }

}

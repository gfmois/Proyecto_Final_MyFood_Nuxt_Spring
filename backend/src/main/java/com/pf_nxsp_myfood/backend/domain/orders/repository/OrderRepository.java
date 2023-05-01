package com.pf_nxsp_myfood.backend.domain.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @EntityGraph("fetch-orders")
    @Query("SELECT o FROM OrderEntity o WHERE o.id_client = :id_client")
    List<OrderEntity> getOrders(@Param("id_client") String id_client);

    @Query("SELECT o FROM OrderEntity o WHERE o.restaurant_orders.id_restaurant = :idRestaurant ")
    List<OrderEntity> findByIdRestaurant(@Param("idRestaurant") String idRestaurant);
}

package com.pf_nxsp_myfood.backend.domain.products.repository;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "SELECT * FROM products p WHERE id_restaurant = :id_restaurant", nativeQuery = true)
    public List<ProductEntity> findAllProductByRestaurant(@Param("id_restaurant") String id_restaurant);
}

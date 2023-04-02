package com.pf_nxsp_myfood.backend.domain.restaurants.repository;

import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// @Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {
    @Query(value = "SELECT DISTINCT category FROM `restaurants`", nativeQuery = true)
    List<String> getCategories();
}

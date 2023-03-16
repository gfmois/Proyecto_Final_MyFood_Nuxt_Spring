package com.pf_nxsp_myfood.backend.domain.restaurants.repository;

import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {
    
}

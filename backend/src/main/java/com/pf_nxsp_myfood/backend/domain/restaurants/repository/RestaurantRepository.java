package com.pf_nxsp_myfood.backend.domain.restaurants.repository;

import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {
    
}

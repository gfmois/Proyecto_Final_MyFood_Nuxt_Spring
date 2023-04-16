package com.pf_nxsp_myfood.backend.domain.reserves.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

public interface ReserveRepository extends JpaRepository<RestaurantEntity, String> {
    
}

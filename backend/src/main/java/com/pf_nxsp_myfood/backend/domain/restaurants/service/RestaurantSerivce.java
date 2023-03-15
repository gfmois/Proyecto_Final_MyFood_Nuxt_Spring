package com.pf_nxsp_myfood.backend.domain.restaurants.service;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import java.util.List;

public interface RestaurantSerivce {
    List<RestaurantDto> getRestaurants();
}

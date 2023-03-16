package com.pf_nxsp_myfood.backend.domain.restaurants.service;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;

import java.util.List;

public interface RestaurantSerivce {
    List<RestaurantDto> getRestaurants();
    MessageResponse saveRestaurant(RestaurantDto rDto);
    MessageResponse updateRestaurant(RestaurantDto rDto);
    MessageResponse deleteRestaurant(String id);
}

package com.pf_nxsp_myfood.backend.domain.restaurants.service;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;

import java.util.List;
import java.util.Map;

public interface RestaurantSerivce {
    List<RestaurantDto> getRestaurants();
    List<RestaurantDto> getRestaurantsByCity(String city);
    Map<String, Object> getRestaurantById(String id);
    Map<String, Object> getRestaurantByIdOrSlug(String id);
    Map<String, List<String>> getRestaurantsInfo();
    MessageResponse saveRestaurant(RestaurantDto rDto);
    MessageResponse updateRestaurant(RestaurantDto rDto);
    MessageResponse deleteRestaurant(String id);
}

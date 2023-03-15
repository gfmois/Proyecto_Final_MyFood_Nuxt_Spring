package com.pf_nxsp_myfood.backend.domain.restaurants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.service.RestaurantSerivce;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
public class RestaurantController {
    @Autowired
     private RestaurantSerivce rService;

     @GetMapping
     public RestaurantDto.MultipleRestaurants getRestaurants() {
         return RestaurantDto.MultipleRestaurants.builder()
             .restaurants(rService.getRestaurants())
             .build();
     }

    @GetMapping(value = "/hello")
    public String helloWorld() {
        return "Hello";
    }
}

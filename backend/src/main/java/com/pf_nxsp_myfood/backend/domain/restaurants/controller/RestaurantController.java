package com.pf_nxsp_myfood.backend.domain.restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, Object> rTemplate;

    @Cacheable(value = "restaurants")
    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return rService.getRestaurants();
    }

    @GetMapping(value = "/hello")
    public String helloWorld() {
        return "Hello";
    }
}

package com.pf_nxsp_myfood.backend.domain.restaurants.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
// import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pf_nxsp_myfood.backend.domain.payload.request.restaurant.RestaurantRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.service.RestaurantSerivce;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
public class RestaurantController {
    @Autowired
    private RestaurantSerivce rService;

    // @Autowired
    // private RedisTemplate<String, Object> rTemplate;

    @Autowired
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @Cacheable(value = "restaurants")
    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return rService.getRestaurants();
    }

    @PostMapping
    public MessageResponse addRestaurant(@RequestBody @Valid RestaurantRequest body) {
        RestaurantDto rDto = new RestaurantDto();
        JsonObject json = gson.fromJson(gson.toJson(body), JsonObject.class);
        
        if (json.get("capacity").getAsInt() <= 0) {
            return new MessageResponse("Capacity must be greater than 0", "400");
        }

        rDto.setId_restaurant(IdGenerator.generateWithLength(20));
        rDto.setName(json.get("name").getAsString());
        rDto.setCapacity(json.get("capacity").getAsInt());
        rDto.setQuality(json.get("quality").getAsString());
        rDto.setLogo(json.get("logo").getAsString());

        return rService.saveRestaurant(rDto);
    }

    @PutMapping
    public MessageResponse updateRestaurant(@RequestBody RestaurantDto body) {
        return rService.updateRestaurant(body);
    }

    @DeleteMapping("/{id_restaurant}")
    public MessageResponse deleteRestaurant(@PathVariable String id_restaurant) {
        return rService.deleteRestaurant(id_restaurant);
    }
}

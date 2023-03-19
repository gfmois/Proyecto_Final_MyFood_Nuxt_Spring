package com.pf_nxsp_myfood.backend.domain.restaurants.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    // @CachePut(value = "restaurants")
    @Cacheable(value = "restaurants")
    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return rService.getRestaurants();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @PostMapping
    public MessageResponse addRestaurant(@RequestBody @Valid RestaurantRequest body) {
        try {
            // RestaurantDto new Instance
            RestaurantDto rDto = new RestaurantDto();

            // Check for Capacity
            if (body.getCapacity() <= 0) {
                return new MessageResponse("Capacity must be greater than 0", "400");
            }

            // Transform RestaurantRequest to RestaurantDto
            rDto.setId_restaurant(IdGenerator.generateWithLength(20));
            rDto.setName(body.getName());
            rDto.setCapacity(body.getCapacity());
            rDto.setLogo(body.getLogo());
            rDto.setCategory(body.getCategory());
            
            return rService.saveRestaurant(rDto);
        } catch (Exception e) {
            return new MessageResponse(String.format("Error: %s", e.getMessage()), "400");
        }
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @PutMapping
    public MessageResponse updateRestaurant(@RequestBody RestaurantDto body) {
        return rService.updateRestaurant(body);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @DeleteMapping("/{id_restaurant}")
    public MessageResponse deleteRestaurant(@PathVariable String id_restaurant) {
        return rService.deleteRestaurant(id_restaurant);
    }
}

package com.pf_nxsp_myfood.backend.domain.restaurants.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pf_nxsp_myfood.backend.domain.common.utils.FileUpload;
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
	private FileUpload fileUpload;

    @Autowired
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    // @CachePut(value = "restaurants")
    @Cacheable(value = "restaurants")
    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return rService.getRestaurants();
    }

    @GetMapping("/{id_restaurant}")
    public RestaurantDto getRestaurantById(@PathVariable String id_restaurant) {
        return rService.getRestaurantById(id_restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @PostMapping(consumes = { "multipart/form-data" })
    public MessageResponse addRestaurant(
        @RequestParam("file") MultipartFile file,
        @RequestParam("name") String name,
        @RequestParam("logo") MultipartFile logo,
        @RequestParam("category") String category,
        @RequestParam("lat") String lat,
        @RequestParam("lng") String lng,
        @RequestParam("city") String city,
        @RequestParam("capacity") Integer capacity
    ) {
        try {
            // RestaurantDto new Instance
            RestaurantDto rDto = new RestaurantDto();

            // Check for Capacity
            if (capacity <= 0) {
                return new MessageResponse("Capacity must be greater than 0", "400");
            }

            // Saves the image and gets the full path of the file to save it into DB
			Map<String, Object> fileObj = fileUpload.saveFile("restaurants", file);
			Map<String, Object> logoObj = fileUpload.saveFile("restaurants/logos", logo);

            if ((Integer) logoObj.get("statusCode") == 400) {
				return new MessageResponse("Error trying to uploading the image",
						String.valueOf((Integer) logoObj.get("status")));
			}

            // Transform Form-Data to RestaurantDto
            rDto.setId_restaurant(IdGenerator.generateWithLength(20));
            rDto.setName(name);
            rDto.setCapacity(capacity);
            rDto.setLogo((String) logoObj.get("path").toString());
            rDto.setImage((String) fileObj.get("path").toString());
            rDto.setCategory(category);
            rDto.setLat(lat);
            rDto.setLng(lng);
            rDto.setCity(city);

            // Saves the new Restaurant
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

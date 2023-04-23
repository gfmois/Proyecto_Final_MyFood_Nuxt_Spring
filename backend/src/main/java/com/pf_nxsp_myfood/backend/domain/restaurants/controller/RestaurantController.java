package com.pf_nxsp_myfood.backend.domain.restaurants.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pf_nxsp_myfood.backend.domain.common.utils.FileUpload;
import com.pf_nxsp_myfood.backend.domain.orders.service.OrderService;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.dto.ProductDto;
import com.pf_nxsp_myfood.backend.domain.products.service.ProductService;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.service.RestaurantSerivce;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*")
public class RestaurantController {
    @Autowired
    private RestaurantSerivce rService;

    @Autowired
    private ProductService pService;

    @Autowired
    private OrderService oService;

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
    public List<RestaurantDto> getRestaurants(HttpServletRequest req) {
        List<RestaurantDto> res = new ArrayList<>();

        if (req.getParameterMap().size() > 0) {
            AtomicBoolean hasCity = new AtomicBoolean(false);
            AtomicBoolean hasPrice = new AtomicBoolean(false);
            AtomicBoolean hasCategory = new AtomicBoolean(false);

            List<RestaurantDto> restaurantsByCity = new ArrayList<>();
            List<RestaurantDto> restaurantsByPrice = new ArrayList<>();
            List<RestaurantDto> restaurantsByCategory = new ArrayList<>();

            req.getParameterMap().entrySet().forEach(e -> {
                if (e.getKey().equals("city")) {
                    hasCity.set(true);
                    restaurantsByCity.addAll(rService.getRestaurantsByCity(String.join(" ", e.getValue())));
                }

                if (e.getKey().equals("price")) {
                    Set<ProductDto> products = new HashSet<>();
                    hasPrice.set(true);

                    pService.getProducts()
                            .stream()
                            .filter(p -> Double.parseDouble(p.getPrice()) >= Double
                                    .parseDouble((String.join(" ", e.getValue()))))
                            .forEach(products::add);

                    products
                            .stream()
                            .map(product -> (RestaurantDto) rService.getRestaurantById(product.getRestaurant())
                                    .get("restaurant"))
                            .forEach(restaurantsByPrice::add);
                }

                if (e.getKey().equals("category")) {
                    hasCategory.set(true);
                    restaurantsByCategory.addAll(rService.getRestaurants().stream()
                            .filter(r -> r.getCategory().equals(String.join(" ", e.getValue())))
                            .collect(Collectors.toList()));
                }
            });

            if (hasCity.get() && hasPrice.get()) {
                res = restaurantsByCity.stream()
                        .filter(restaurantsByPrice::contains)
                        .filter(restaurantsByCategory::contains)
                        .map(r -> (RestaurantDto) r)
                        .collect(Collectors.toList());
            } else if (hasCity.get()) {
                res = restaurantsByCity;
            } else if (hasPrice.get()) {
                res = restaurantsByPrice;
            } else if (hasCategory.get()) {
                res = restaurantsByCategory;
            }

        } else {
            res = rService.getRestaurants();
        }

        return res;
    }

    @GetMapping("/{id_restaurant}")
    public Map<String, Object> getRestaurantById(@PathVariable String id_restaurant) {
        return rService.getRestaurantByIdOrSlug(id_restaurant);
    }

    @GetMapping("/count")
    public Map<String, Integer> countRestaurants() {
        Map<String, Integer> rSize = new HashMap<String, Integer>();

        rSize.put("restaurants", rService.getRestaurants().size());
        rSize.put("orders", oService.getOrders().size());

        return rSize;
    }

    @GetMapping(value = "/{id_restaurant}/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getRestaurantImage(@PathVariable String id_restaurant)
            throws IOException {
        Map<String, Object> pDto = rService.getRestaurantById(id_restaurant);
        String str_imagePath = ((RestaurantDto) pDto.get("restaurant")).getImage();
        String[] imageSplitted = str_imagePath.split("/");
        String image = imageSplitted[imageSplitted.length - 1];

        Path imagePath = Paths.get("./assets/images/restaurants", image);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
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
            @RequestParam("capacity") Integer capacity) {
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
            rDto.setSlug(
                    String.format("%s-%s", name.toLowerCase().replaceAll(" ", "-"), IdGenerator.generateWithLength(8)));

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

    @CacheEvict(value = "restaurants", allEntries = true)
    @GetMapping("/info")
    public Map<String, List<String>> getRestaurantsInfo() {
        return rService.getRestaurantsInfo();
    }
}

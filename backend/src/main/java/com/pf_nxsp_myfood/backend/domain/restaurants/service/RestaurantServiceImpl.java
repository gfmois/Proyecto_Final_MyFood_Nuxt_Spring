package com.pf_nxsp_myfood.backend.domain.restaurants.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.dto.ProductDto;
import com.pf_nxsp_myfood.backend.domain.products.service.ProductService;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;
import com.pf_nxsp_myfood.backend.domain.restaurants.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component

public class RestaurantServiceImpl implements RestaurantSerivce {
	private final RestaurantRepository rRepository;

	private RestaurantDto convertEntityToDto(RestaurantEntity rEntity) {
		return RestaurantDto.builder()
				.id_restaurant(rEntity.getId_restaurant())
				.name(rEntity.getName())
				.capacity(rEntity.getCapacity())
				.logo(rEntity.getLogo())
				.image(rEntity.getImage())
				.category(rEntity.getCategory())
				.lat(rEntity.getLat())
				.lng(rEntity.getLng())
				.city(rEntity.getCity())
				.products(rEntity
					.getProducts()
					.stream()
					.map(e -> e.getId_product())
					.collect(Collectors.toList())
				)
				.build();
	}

	private RestaurantEntity convertDtoToEntity(RestaurantDto rDto) {
		return RestaurantEntity.builder()
				.id_restaurant(rDto.getId_restaurant())
				.name(rDto.getName())
				.capacity(rDto.getCapacity())
				.logo(rDto.getLogo())
				.image(rDto.getImage())
				.category(rDto.getCategory())
				.lat(rDto.getLat())
				.lng(rDto.getLng())
				.city(rDto.getCity())
				.build();
	}

	@Override
	public List<RestaurantDto> getRestaurants() {
		return rRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, Object> getRestaurantById(String id) {
		Map<String, Object> obj = new HashMap<String, Object>();
		RestaurantDto rDto = convertEntityToDto(rRepository.findById(id).get());
		List<ProductDto> productList = new ArrayList<ProductDto>();
		final ProductService pService;

		obj.put("restaurant", rDto);
		
		// TODO: Implement getProductByRestaurantId in ProductService to get All products of an restaurant
		for (String pdId : rDto.getProducts()) {
			// productList.add(pService.getProductByRestaurantId());
		}
		
		obj.put("products", productList);
		return obj;
	}

	@Override
	public MessageResponse saveRestaurant(RestaurantDto rDto) {
		try {
			rRepository.save(convertDtoToEntity(rDto));
			return new MessageResponse("Restaurant Created Correctly", "201");
		} catch (Exception e) {
			return new MessageResponse("There was an problem creating the restaurant", "401");
		}
	}

	@Override
	public MessageResponse updateRestaurant(RestaurantDto rDto) {
		try {
			if (!rRepository.existsById(rDto.getId_restaurant())) {
				return new MessageResponse("No Restaurant with that ID", "401");
			}

			RestaurantEntity rEntity = rRepository.findById(rDto.getId_restaurant()).get();

			rEntity.setName(rDto.getName());
			rEntity.setCapacity(rDto.getCapacity());
			rEntity.setLogo(rDto.getLogo());
			rEntity.setCategory(rDto.getCategory());
			rEntity.setLat(rDto.getLat());
			rEntity.setLng(rDto.getLng());
			rEntity.setCity(rDto.getCity());
			rEntity.setImage(rDto.getImage());

			rRepository.save(rEntity);

			return new MessageResponse("Restaurant updated successfully", "200");
		} catch (Exception e) {
			return new MessageResponse(String.format("Error updating: %s", e.getMessage()), "400");
		}
	}

	@Override
	public MessageResponse deleteRestaurant(String id) {
		try {
			rRepository.deleteById(id);
			return new MessageResponse(String.format("Restaurant with ID '%s' deleted", id), "200");
		} catch (Exception e) {
			return new MessageResponse(String.format("Error: %s", e.getMessage()), "400");
		}
	}
}
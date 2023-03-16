package com.pf_nxsp_myfood.backend.domain.restaurants.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;
import com.pf_nxsp_myfood.backend.domain.restaurants.repository.RestaurantRepository;

import jakarta.transaction.Transactional;
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
				.quality(rEntity.getQuality())
				.capacity(rEntity.getCapacity())
				.build();
	}

	@Transactional
	@Override
	public List<RestaurantDto> getRestaurants() {
		return rRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
}

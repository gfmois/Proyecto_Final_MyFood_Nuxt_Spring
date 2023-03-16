package com.pf_nxsp_myfood.backend.domain.restaurants.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
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
				.quality(rEntity.getQuality())
				.capacity(rEntity.getCapacity())
				.build();
	}

	private RestaurantEntity convertDtoToEntity(RestaurantDto rDto) {
		return RestaurantEntity.builder()
				.id_restaurant(rDto.getId_restaurant())
				.name(rDto.getName())
				.capacity(rDto.getCapacity())
				.quality(rDto.getQuality())
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
	public MessageResponse saveRestaurant(RestaurantDto rDto) {
		if (rRepository.save(convertDtoToEntity(rDto)) != null) {
			return new MessageResponse("Restaurant Created Correctly", "201");
		}

		return new MessageResponse("There was an problem creating the restaurant", "401");
	}

	@Override
	public MessageResponse updateRestaurant(RestaurantDto rDto) {
		try {
			if (!rRepository.existsById(rDto.getId_restaurant())) {
				return new MessageResponse("No Restaurant with that ID", "401");
			}

			RestaurantEntity rEntity = rRepository.findById(rDto.getId_restaurant()).get();
			rEntity.setName(rDto.getName());
			rEntity.setQuality(rDto.getQuality());
			rEntity.setCapacity(rDto.getCapacity());
			rEntity.setLogo(rDto.getLogo());

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

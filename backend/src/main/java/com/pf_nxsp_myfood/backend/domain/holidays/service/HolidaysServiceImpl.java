package com.pf_nxsp_myfood.backend.domain.holidays.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.holidays.dto.HolidayDto;
import com.pf_nxsp_myfood.backend.domain.holidays.entity.HolidayEntity;
import com.pf_nxsp_myfood.backend.domain.holidays.repository.HolidaysRepository;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component
public class HolidaysServiceImpl implements HolidaysService {
    @Autowired
    private HolidaysRepository hRepository;

    private HolidayDto convertToDto(HolidayEntity entity) {
        return HolidayDto.builder()
            .description(entity.getDescription())
            .holiday_date(entity.getHoliday_date())
            .id_holiday(entity.getId_holiday())
            .restaurant(entity.getRestaurant_holidays().getId_restaurant())
            .build();
    }

    private HolidayEntity convertToEntity(HolidayDto dto) {
        return HolidayEntity
            .builder()
            .description(dto.getDescription())
            .holiday_date(dto.getHoliday_date())
            .restaurant_holidays(RestaurantEntity.builder().id_restaurant(dto.getRestaurant()).build())
            .id_holiday(dto.getId_holiday()).build();
    }

    @Override
    public List<HolidayDto> getRestaurantHolidays(String id_restaurant) {
        return hRepository
                .findAll()
                .stream()
                .filter(e -> e.getRestaurant_holidays().getId_restaurant().equals(id_restaurant))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HolidayDto getHoliday(String id_holiday) {
        return convertToDto(hRepository.findById(id_holiday).get());
    }

    @Override
    public Boolean deleteHoliday(String id_holiday) {
        try {
            hRepository.delete(hRepository.findById(id_holiday).get());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public HolidayDto updateHoliday(HolidayDto holiday) {
        HolidayEntity h = hRepository.findById(holiday.getId_holiday()).get();

        h.setDescription(holiday.getDescription());
        h.setHoliday_date(holiday.getHoliday_date());

        if (hRepository.save(h) != null) {
            return convertToDto(h);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> createHoliday(HolidayDto holiday) {
        if (hRepository.save(convertToEntity(holiday)) != null) {
            return ResponseEntity.ok().body(Map.of("status", 200, "message", "Holiday Created"));
        }

        return ResponseEntity.ok().body(Map.of("status", 400, "message", "Error while trying to create the Holiday"));
    }
}

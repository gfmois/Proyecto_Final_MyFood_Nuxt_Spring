package com.pf_nxsp_myfood.backend.domain.holidays.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.holidays.dto.HolidayDto;
import com.pf_nxsp_myfood.backend.domain.holidays.entity.HolidayEntity;
import com.pf_nxsp_myfood.backend.domain.holidays.repository.HolidaysRepository;

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
}

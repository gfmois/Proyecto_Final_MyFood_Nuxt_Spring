package com.pf_nxsp_myfood.backend.domain.holidays.service;

import java.util.List;

import com.pf_nxsp_myfood.backend.domain.holidays.dto.HolidayDto;

public interface HolidaysService {
    List<HolidayDto> getRestaurantHolidays(String id_restaurant);
    HolidayDto getHoliday(String id_holiday);
    Boolean deleteHoliday(String id_holiday);
}

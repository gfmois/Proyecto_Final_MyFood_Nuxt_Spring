package com.pf_nxsp_myfood.backend.domain.holidays.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HolidayDto {
    @NotNull
    @NotEmpty
    private String id_holiday;

    @NotNull
    @NotEmpty
    @NotBlank
    private Date holiday_date;

    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @NotNull
    private String restaurant;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class SingleHoliday<T> {
        private T holiday;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MultipleHolidays {
        private List<HolidayDto> holidays;
    }
}

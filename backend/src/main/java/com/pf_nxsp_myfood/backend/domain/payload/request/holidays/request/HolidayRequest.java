package com.pf_nxsp_myfood.backend.domain.payload.request.holidays.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

public class HolidayRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter
    private String description;

    @NotNull
    @NotEmpty
    @NotBlank
    @Getter
    private Date holiday_date;
}

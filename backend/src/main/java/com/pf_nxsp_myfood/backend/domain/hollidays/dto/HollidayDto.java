package com.pf_nxsp_myfood.backend.domain.hollidays.dto;

import java.sql.Date;

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
public class HollidayDto {
    @NotNull
    @NotEmpty
    private String id_holliday;

    @NotNull
    @NotEmpty
    @NotBlank
    private Date holliday_date;

    @NotNull
    @NotEmpty
    @NotBlank
    private String description;
}

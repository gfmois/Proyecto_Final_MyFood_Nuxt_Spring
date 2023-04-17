package com.pf_nxsp_myfood.backend.domain.payload.request.reserve;


import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReserveRequest {
    @NotBlank
    @NotNull
    @NotEmpty
    private Date date_reserve;

    @NotBlank
    @NotNull
    @NotEmpty
    private ReservesTypes type;

    @NotBlank
    @NotNull
    @NotEmpty
    private Integer diners;

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotBlank
    @NotNull
    @NotEmpty
    private String id_restaurant;
}

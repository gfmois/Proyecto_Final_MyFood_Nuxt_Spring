package com.pf_nxsp_myfood.backend.domain.payload.request.reserve;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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
    private Integer dinners;

    @NotBlank
    @NotNull
    @NotEmpty
    private String status;

    @NotBlank
    @NotNull
    @NotEmpty
    private String id_client;

    @NotBlank
    @NotNull
    @NotEmpty
    private String id_restaurant;
}

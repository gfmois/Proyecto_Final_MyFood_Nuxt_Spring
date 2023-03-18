package com.pf_nxsp_myfood.backend.domain.payload.request.restaurant;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequest extends BaseUtils {
    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 1)
    private Integer capacity;

    @NotBlank
    @NotNull
    @NotEmpty
    private String quality;

    @NotBlank
    @NotNull
    @NotEmpty
    private String logo;
}


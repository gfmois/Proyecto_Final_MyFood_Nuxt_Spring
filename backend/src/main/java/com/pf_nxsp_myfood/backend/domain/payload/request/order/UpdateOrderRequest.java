package com.pf_nxsp_myfood.backend.domain.payload.request.order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;

import lombok.Getter;

public class UpdateOrderRequest {
    @NotBlank
    @NotEmpty
    @NotNull
    @Getter
    private String id_order;

    @NotNull
    @NotEmpty
    @NotNull
    @Getter
    private OrderTypes status;
}

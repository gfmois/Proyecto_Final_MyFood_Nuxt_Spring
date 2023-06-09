package com.pf_nxsp_myfood.backend.domain.payload.request.order;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;
import com.pf_nxsp_myfood.backend.domain.orders.entity.OrderProductEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewOrderRequest extends BaseUtils {
    @NotBlank
    @NotEmpty
    @NotNull
    private Set<OrderProductEntity> products;

    @NotBlank
    @NotEmpty
    @NotNull
    private String card_token;

    @NotNull
    @NotEmpty
    @NotNull
    private String id_restaurant;
}

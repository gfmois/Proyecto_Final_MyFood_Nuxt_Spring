package com.pf_nxsp_myfood.backend.domain.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderProductDto {
    private String id_order;
    private String id_product;
    private Integer quantity;
}

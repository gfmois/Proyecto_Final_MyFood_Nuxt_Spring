package com.pf_nxsp_myfood.backend.domain.orders.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    @NotNull
    private String id_order;

    @NotNull
    private String id_client; 

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private OrderTypes status;

    private List<?> product_ordered;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SingleOrder<T> {
        private T order;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class MultipleOrders {
        private List<OrderDto> orders;
    }
}
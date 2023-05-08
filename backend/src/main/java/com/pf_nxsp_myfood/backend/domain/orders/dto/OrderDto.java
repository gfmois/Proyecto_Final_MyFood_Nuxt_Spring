package com.pf_nxsp_myfood.backend.domain.orders.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto extends BaseUtils implements Serializable {
    @NotNull
    private String id_order;

    @NotNull
    private String id_client;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private OrderTypes status;

    private List<?> product_ordered;

    @NotNull
    @NotEmpty
    private String id_restaurant;

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

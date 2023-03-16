package com.pf_nxsp_myfood.backend.domain.restaurants.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestaurantDto implements Serializable {
    @NotNull
    private String id_restaurant;

    @NotNull
    private String name;

    @NotNull
    private Integer capacity;

    @NotNull
    private String quality;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class SingleRestaurant<T> {
        private T restaurant;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class MultipleRestaurants {
        private List<RestaurantDto> restaurants;
    }
}

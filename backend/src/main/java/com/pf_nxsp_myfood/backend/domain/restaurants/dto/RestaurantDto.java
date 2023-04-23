package com.pf_nxsp_myfood.backend.domain.restaurants.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

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
public class RestaurantDto extends BaseUtils implements Serializable {
    @NotNull
    private String id_restaurant;

    @NotNull
    private String name;

    @NotNull
    private Integer capacity;

    @NotNull
    private String logo;

    @NotNull
    private String image;

    @NotNull
    private String slug;

    @NotNull
    private String category;

    @NotNull
    private String lat;

    @NotNull
    private String lng;

    @NotNull
    private String city;

    @NotNull
    private List<String> products;

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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof RestaurantDto)) {
            return false;
        }

        RestaurantDto other = (RestaurantDto) o;

        return id_restaurant == other.id_restaurant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_restaurant);
    }

}

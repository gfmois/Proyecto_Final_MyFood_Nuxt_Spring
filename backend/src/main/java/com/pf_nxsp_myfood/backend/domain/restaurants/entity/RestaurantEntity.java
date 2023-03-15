package com.pf_nxsp_myfood.backend.domain.restaurants.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
@NamedEntityGraph(name = "fetch-restaurants")
public class RestaurantEntity {
    @Id
    private String id_restaurant;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "quality")
    private String quality;

    @Builder
    public RestaurantEntity(String id_restaurant, String name, Integer capacity, String quality) {
        this.id_restaurant = id_restaurant;
        this.name = name;
        this.capacity = capacity;
        this.quality = quality;
    }
}

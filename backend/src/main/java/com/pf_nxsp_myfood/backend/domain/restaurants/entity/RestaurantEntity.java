package com.pf_nxsp_myfood.backend.domain.restaurants.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    @Column(name = "logo")
    private String logo;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "restaurant")
    private Set<ProductEntity> products;

    @Builder
    public RestaurantEntity(String id_restaurant, String name, Integer capacity, String logo, String category) {
        this.id_restaurant = id_restaurant;
        this.name = name;
        this.capacity = capacity;
        this.logo = logo;
        this.category = category;
    }

}

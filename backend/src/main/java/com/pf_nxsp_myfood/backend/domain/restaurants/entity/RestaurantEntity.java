package com.pf_nxsp_myfood.backend.domain.restaurants.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;
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
public class RestaurantEntity extends BaseUtils {
    @Id
    private String id_restaurant;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;
    
    @Column(name = "logo")
    private String logo;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "restaurant")
    private List<ProductEntity> products;

    @Builder
    public RestaurantEntity(String id_restaurant, String name, Integer capacity, String logo, String image, String category, String lat, String lng, String city) {
        this.id_restaurant = id_restaurant;
        this.name = name;
        this.capacity = capacity;
        this.logo = logo;
        this.image = image;
        this.category = category;
        this.lat = lat;
        this.lng = lng;
        this.city = city;
        this.products = new ArrayList<>();
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public void addProduct(ProductEntity pEntity) {
        this.products.add(pEntity);
    }

}

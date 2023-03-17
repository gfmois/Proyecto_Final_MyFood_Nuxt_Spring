package com.pf_nxsp_myfood.backend.domain.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@NamedEntityGraph(name = "fetch-products")
public class ProductEntity {
    @Id
    private String id_product;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant;

    @Builder
    public ProductEntity(String id_product, String slug, String name, String price, String image) {
        this.id_product = id_product;
        this.slug = slug;
        this.name = name;
        this.price = price;
        this.image = image;
        this.restaurant = new RestaurantEntity();
    }

}

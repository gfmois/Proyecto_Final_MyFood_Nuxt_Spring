package com.pf_nxsp_myfood.backend.domain.allergens.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "allergens")
@NamedEntityGraph(name = "fetch-allergens")
public class AllergenEntity {
    @Id
    private String id_allergen;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @ManyToMany(
        mappedBy = "allergens",
        fetch = FetchType.LAZY, 
        cascade = CascadeType.ALL
    )
    private List<ProductEntity> products = new ArrayList<>();

    @Builder
    public AllergenEntity(String id_allergen, String name, String icon) {
        this.id_allergen = id_allergen;
        this.name = name;
        this.icon = icon;
        this.products = new ArrayList<>();
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}

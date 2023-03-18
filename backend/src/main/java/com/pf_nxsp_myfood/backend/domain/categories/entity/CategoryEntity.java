package com.pf_nxsp_myfood.backend.domain.categories.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

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
@Table(name = "categories")
@NamedEntityGraph(name = "fetch-categories")
public class CategoryEntity {
    @Id
    private String id_category;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;
    
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "categories"
)
    private List<ProductEntity> products = new ArrayList<>();

    @Builder
    public CategoryEntity(String id_category, String slug, String name, String icon) {
        // NOTE: this.products will need an if to check if comes emtpy or not.
        this.id_category = id_category;
        this.slug = slug;
        this.name = name;
        this.icon = icon;
        this.products = new ArrayList<ProductEntity>();
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public void setProduct(List<ProductEntity> products) {
        this.products = products;
    }

}

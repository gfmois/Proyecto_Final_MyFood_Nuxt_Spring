package com.pf_nxsp_myfood.backend.domain.products.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.allergens.entity.AllergenEntity;
import com.pf_nxsp_myfood.backend.domain.categories.entity.CategoryEntity;
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

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "product_allergens", 
        joinColumns = @JoinColumn(name = "id_product"), 
        inverseJoinColumns = @JoinColumn(name = "id_allergen")
    )
    private List<AllergenEntity> allergens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant;

    @Builder
    public ProductEntity(String id_product, String slug, String name, String price, String image, String id__restaurant) {
        this.id_product = id_product;
        this.slug = slug;
        this.name = name;
        this.price = price;
        this.image = image;
        this.restaurant = new RestaurantEntity();
        this.categories = new ArrayList<>();
        this.allergens = new ArrayList<>();

        this.restaurant.setId_restaurant(id__restaurant);
    }

    // Get ManyToMany Methods
    public List<CategoryEntity> getCategories() {
        return this.categories;
    }

    public List<AllergenEntity> getAllergens() {
        return this.allergens;
    }

    // Add ManyToMany Methods
    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void addAllergen(AllergenEntity allergen) {
        this.allergens.add(allergen);
        allergen.getProducts().add(this);
    }

    // Remove ManyToMany Methods
    public void removeCategory(String cateogyId) {
        CategoryEntity cat = this.categories
                .stream()
                .filter(c -> c.getId_category() == cateogyId)
                .findFirst()
                .orElse(null);

        if (cat != null) {
            this.categories.remove(cat);
            cat.getProducts().remove(this);
        }
    }

    public void removeAllergen(String allergenId) {
        AllergenEntity allergen = this.allergens
                .stream()
                .filter(a -> a.getId_allergen() == allergenId)
                .findFirst()
                .orElse(null);

        if (allergen != null) {
            this.allergens.remove(allergen);
            allergen.getProducts().remove(this);
        }
    }

}

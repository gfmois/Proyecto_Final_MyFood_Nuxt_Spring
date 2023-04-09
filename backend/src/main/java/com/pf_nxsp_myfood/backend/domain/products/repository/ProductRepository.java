package com.pf_nxsp_myfood.backend.domain.products.repository;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @Query(value = "SELECT * FROM products p WHERE id_restaurant = :id_restaurant", nativeQuery = true)
    public List<ProductEntity> findAllProductByRestaurant(@Param("id_restaurant") String id_restaurant);

    @Query(value = "SELECT * FROM products p WHERE p.id_product = :id_product", nativeQuery = true)
    public ProductEntity findById_product(@Param("id_product") String id_product);

    @Query(value = "SELECT * FROM products p WHERE p.slug = :slug", nativeQuery = true)
    public ProductEntity findBySlug(@Param("slug") String slug);

    @Query(value = "SELECT COUNT(*) FROM products p " +
            "INNER JOIN product_category c " +
            "ON c.id_product = p.id_product " +
            "WHERE c.id_category IN(:categories) " +
            "AND p.price BETWEEN :rango1 AND :rango2 ", nativeQuery = true)
    public Integer getFilteredProductsCategoriesLength(@Param("categories") List<String> categories,  @Param("rango1") String rango1, @Param("rango2") String rango2);

    @Query(
        value = "SELECT COUNT(*) FROM products p " + 
                "INNER JOIN product_category c " + 
                "ON c.id_product = p.id_product " +
                "WHERE p.price BETWEEN :rango1 AND :rango2 ",
        nativeQuery = true)
    public Integer getFilteredProductsLength(@Param("rango1") String rango1, @Param("rango2") String rango2);

    @Query(
        value = "SELECT p.* FROM products p " + 
                "INNER JOIN product_category c " + 
                "ON c.id_product = p.id_product " +
                "WHERE c.id_category IN(:categories) " +
                "AND p.price BETWEEN :rango1 AND :rango2 " +
                "ORDER BY CASE WHEN :orden = 'ASC' THEN p.price END ASC, " + 
                "CASE WHEN :orden = 'DESC' THEN p.price END DESC " +
                "LIMIT :paginacion, 12",
        nativeQuery = true)
    public List<ProductEntity> getFilteredProductsCategories(@Param("categories") List<String> categories, @Param("orden") String orden, @Param("rango1") String rango1, @Param("rango2") String rango2, @Param("paginacion") Integer paginacion);

    @Query(
        value = "SELECT p.* FROM products p " + 
                "INNER JOIN product_category c " + 
                "ON c.id_product = p.id_product " +
                "WHERE p.price BETWEEN :rango1 AND :rango2 " +
                "ORDER BY CASE WHEN :orden = 'ASC' THEN p.price END ASC, " + 
                "CASE WHEN :orden = 'DESC' THEN p.price END DESC " +
                "LIMIT :paginacion, 12",
        nativeQuery = true)
    public List<ProductEntity> getFilteredProducts(@Param("orden") String orden, @Param("rango1") String rango1, @Param("rango2") String rango2, @Param("paginacion") Integer paginacion);

    @Query(
        value = "SELECT p.* FROM products p " +
                "WHERE p.name LIKE %:product% ",
        nativeQuery = true)
    public List<ProductEntity> searchProducts(@Param("product") String product);
}

package com.pf_nxsp_myfood.backend.domain.products.repository;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    
}

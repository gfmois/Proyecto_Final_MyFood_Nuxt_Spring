package com.pf_nxsp_myfood.backend.domain.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_nxsp_myfood.backend.domain.categories.entity.CategoryEntity;

public interface CategoryRespository extends JpaRepository<CategoryEntity, String> {
    
}

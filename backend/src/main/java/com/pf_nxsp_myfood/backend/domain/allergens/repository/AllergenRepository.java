package com.pf_nxsp_myfood.backend.domain.allergens.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_nxsp_myfood.backend.domain.allergens.entity.AllergenEntity;

public interface AllergenRepository extends JpaRepository<AllergenEntity, String> {
}

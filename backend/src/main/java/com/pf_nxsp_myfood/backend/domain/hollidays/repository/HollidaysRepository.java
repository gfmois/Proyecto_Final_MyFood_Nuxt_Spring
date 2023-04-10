package com.pf_nxsp_myfood.backend.domain.hollidays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_nxsp_myfood.backend.domain.hollidays.entity.HollidayEntity;

public interface HollidaysRepository extends JpaRepository<HollidayEntity, String> {
    
}

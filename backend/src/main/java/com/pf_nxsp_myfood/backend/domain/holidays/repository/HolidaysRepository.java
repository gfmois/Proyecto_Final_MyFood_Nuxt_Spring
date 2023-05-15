package com.pf_nxsp_myfood.backend.domain.holidays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_nxsp_myfood.backend.domain.holidays.entity.HolidayEntity;

public interface HolidaysRepository extends JpaRepository<HolidayEntity, String> {

}

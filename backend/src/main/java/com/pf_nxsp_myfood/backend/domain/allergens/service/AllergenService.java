package com.pf_nxsp_myfood.backend.domain.allergens.service;

import java.util.List;

import com.pf_nxsp_myfood.backend.domain.allergens.dto.AllergenDto;

public interface AllergenService {
    List<AllergenDto> getAllergens();
    AllergenDto getAllergenById(String id);
}

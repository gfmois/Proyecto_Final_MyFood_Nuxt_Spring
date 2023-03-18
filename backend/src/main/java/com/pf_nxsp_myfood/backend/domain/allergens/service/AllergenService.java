package com.pf_nxsp_myfood.backend.domain.allergens.service;

import java.util.List;

import com.pf_nxsp_myfood.backend.domain.allergens.dto.AllergenDto;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;

public interface AllergenService {
    List<AllergenDto> getAllergens();
    AllergenDto getAllergenById(String id);
    MessageResponse createAllergen(AllergenDto aDto);
    MessageResponse updateAllergen(AllergenDto aDto);
    MessageResponse deleteAllergen(String id_allergen);
}

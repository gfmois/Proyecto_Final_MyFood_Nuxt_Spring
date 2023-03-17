package com.pf_nxsp_myfood.backend.domain.allergens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.allergens.dto.AllergenDto;
import com.pf_nxsp_myfood.backend.domain.allergens.service.AllergenService;

@RestController
@RequestMapping("/allergens")
@CrossOrigin(origins = "*")
public class AllergenController {
    @Autowired
    private AllergenService aService;

    @GetMapping
    public AllergenDto.MultipleAllergens getAllergens() {
        return AllergenDto.MultipleAllergens
                .builder()
                .allergens(aService.getAllergens())
                .build();
    }

    @GetMapping("/{id_allergen}")
    public AllergenDto.SingleAllergen<?> getAllergen(@PathVariable String id_allergen) {
        return AllergenDto.SingleAllergen
                .builder()
                .allergen(aService.getAllergenById(id_allergen))
                .build();
    }
}

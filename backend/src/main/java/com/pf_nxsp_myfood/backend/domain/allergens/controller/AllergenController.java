package com.pf_nxsp_myfood.backend.domain.allergens.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.allergens.dto.AllergenDto;
import com.pf_nxsp_myfood.backend.domain.allergens.service.AllergenService;
import com.pf_nxsp_myfood.backend.domain.payload.request.allergen.AllergenRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.allergen.UpdateAllergenRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

@RestController
@RequestMapping("/allergens")
@CrossOrigin(origins = "*")
public class AllergenController {
    @Autowired
    private AllergenService aService;

    // Return all allergens and the products that have it
    @GetMapping
    public AllergenDto.MultipleAllergens getAllergens() {
        return AllergenDto.MultipleAllergens
                .builder()
                .allergens(aService.getAllergens())
                .build();
    }

    // Returns Allergen by Id
    @GetMapping("/{id_allergen}")
    public AllergenDto.SingleAllergen<?> getAllergen(@PathVariable String id_allergen) {
        return AllergenDto.SingleAllergen
                .builder()
                .allergen(aService.getAllergenById(id_allergen))
                .build();
    }

    // Create new allergen
    @PostMapping
    public MessageResponse createAllergen(@RequestBody @Valid AllergenRequest allergenBody) {
        // New Instance of AllergenDto
        AllergenDto aDto = new AllergenDto();

        // Transforms AllergenRequest to AllergenDto
        aDto.setId_allergen(IdGenerator.generateWithLength(20));
        aDto.setIcon(allergenBody.getIcon());
        aDto.setName(allergenBody.getName());

        // Saves Allergen
        return aService.createAllergen(aDto);
    }

    @PutMapping
    public MessageResponse updateAllergen(@RequestBody @Valid UpdateAllergenRequest aBody) {
        // New Instance of AllergenDto
        AllergenDto aDto = new AllergenDto();

        // Transforms AllergenRequest to AllergenDto
        aDto.setId_allergen(aBody.getId_allergen());
        aDto.setIcon(aBody.getIcon());
        aDto.setName(aBody.getName());

        // Updates Allergen
        return aService.updateAllergen(aDto);
    }

    @DeleteMapping("/{id_allergen}")
    public MessageResponse deleteAllergen(@PathVariable String id_allergen) {
        return aService.deleteAllergen(id_allergen);
    }
}

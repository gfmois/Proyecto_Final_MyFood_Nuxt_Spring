package com.pf_nxsp_myfood.backend.domain.allergens.service;

import java.util.List;

import com.pf_nxsp_myfood.backend.domain.allergens.dto.AllergenDto;
import com.pf_nxsp_myfood.backend.domain.allergens.entity.AllergenEntity;
import com.pf_nxsp_myfood.backend.domain.allergens.repository.AllergenRepository;
import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

public class AllergenServiceImpl implements AllergenService {
    private AllergenRepository allergenRepository;

    private AllergenDto convertoToDto(AllergenEntity entity) {
        return AllergenDto.builder()
                .id_allergen(entity.getId_allergen())
                .name(entity.getName())
                .icon(entity.getIcon())
                .products(entity.getProducts()
                        .stream()
                        .map(ProductEntity::getId_product)
                        .toList())
                .build();
    }

    @Override
    public List<AllergenDto> getAllergens() {
        return this.allergenRepository
                .findAll()
                .stream()
                .map(this::convertoToDto)
                .toList();
    }

    @Override
    public AllergenDto getAllergenById(String id) {
        return convertoToDto(this.allergenRepository.findById(id).get());
    }
}

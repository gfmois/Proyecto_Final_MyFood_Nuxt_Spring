package com.pf_nxsp_myfood.backend.domain.allergens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.allergens.dto.AllergenDto;
import com.pf_nxsp_myfood.backend.domain.allergens.entity.AllergenEntity;
import com.pf_nxsp_myfood.backend.domain.allergens.repository.AllergenRepository;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import lombok.RequiredArgsConstructor;

@Controller
@Service
@RequiredArgsConstructor
public class AllergenServiceImpl implements AllergenService {
    @Autowired
    private AllergenRepository aRepository;

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

    private AllergenEntity convertDtoToEntity(AllergenDto aDto) {
        return AllergenEntity.builder()
            .id_allergen(aDto.getId_allergen())
            .name(aDto.getName())
            .icon(aDto.getIcon())
            .build();
    }

    @Override
    public List<AllergenDto> getAllergens() {
        return aRepository
                .findAll()
                .stream()
                .map(this::convertoToDto)
                .toList();
    }

    @Override
    public AllergenDto getAllergenById(String id) {
        return convertoToDto(this.aRepository.findById(id).get());
    }

    @Override
    public MessageResponse createAllergen(AllergenDto aDto) {
        // Try to save the allergen, 
        // if can't do it sends error message to client and prints error in console
        try {
            aRepository.save(convertDtoToEntity(aDto));
            return new MessageResponse("Allergen Created Correctly", "200");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new MessageResponse("Error while trying to create Allergen", "400");
        }
    }

    @Override
    public MessageResponse updateAllergen(AllergenDto aDto) {
        try {
            // Checks if Allergen exists
            if (!aRepository.existsById(aDto.getId_allergen())) {
                return new MessageResponse("No Allergen with that ID", "401");
            }

            // Get the Allergen from DB to Update it
            AllergenEntity aEntity = aRepository.findById(aDto.getId_allergen()).get();

            // Update the Allergen
            aEntity.setIcon(aDto.getIcon());
            aEntity.setName(aDto.getName());

            // Save Allergen updated
            aRepository.save(aEntity);

            return new MessageResponse("Allergen Updated Correctly", "200");
        } catch (Exception e) {
            return new MessageResponse(String.format("Error updating: %s", e.getMessage()), "400");
        }
    }

    @Override
    public MessageResponse deleteAllergen(String id_allergen) {
        try {
            aRepository.deleteById(id_allergen);
            return new MessageResponse(String.format("Allergen with ID '%s' deleted", id_allergen), "200");
        } catch (Exception e) {
            return new MessageResponse(String.format("Error: %s", e.getMessage()), "400");
        }
    }
}

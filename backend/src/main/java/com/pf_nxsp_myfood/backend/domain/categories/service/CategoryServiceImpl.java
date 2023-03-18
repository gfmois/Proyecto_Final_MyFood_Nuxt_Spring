package com.pf_nxsp_myfood.backend.domain.categories.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.categories.dto.CategoryDto;
import com.pf_nxsp_myfood.backend.domain.categories.entity.CategoryEntity;
import com.pf_nxsp_myfood.backend.domain.categories.repository.CategoryRespository;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import lombok.AllArgsConstructor;

@Controller
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRespository cRepository;

    private CategoryDto convertEntityToDto(CategoryEntity cEntity) {
        return CategoryDto.builder()
                .id_category(cEntity.getId_category())
                .name(cEntity.getName())
                .slug(cEntity.getSlug())
                .icon(cEntity.getIcon())
                .products(cEntity.getProducts()
                        .stream()
                        .map(ProductEntity::getId_product)
                        .collect(Collectors.toList()))
                .build();
    }

    private CategoryEntity convertDtoToEntity(CategoryDto cDto) {
        return CategoryEntity.builder()
                .id_category(cDto.getId_category())
                .name(cDto.getName())
                .slug(cDto.getSlug())
                .icon(cDto.getIcon())
                .build();
    }

    @Override
    public List<CategoryDto> getCategories() {
        return cRepository.
                findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        return convertEntityToDto(cRepository.findById(id).get());
    }

    @Override
    public MessageResponse createCategory(CategoryDto cDto) {
        // Try to save the Category,
        // if can't do it sends error message to client and prints error in console
        try {
            cRepository.save(this.convertDtoToEntity(cDto));
            return new MessageResponse("Category Created Correctly", "200");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new MessageResponse("Error while trying to create Category", "400");
        }
    }

    @Override
    public MessageResponse updateCategory(CategoryDto cDto) {
        try {
            // Checks if category exists
            if (!cRepository.existsById(cDto.getId_category())) {
                return new MessageResponse("No Category with that ID", "401");
            }

            // Get the Category from DB to Update it
            CategoryEntity cEntity = cRepository.findById(cDto.getId_category()).get();

            // Update the category
            cEntity.setIcon(cDto.getIcon());
            cEntity.setName(cDto.getName());
            cEntity.setSlug(cDto.getSlug());

            // Save category updated
            cRepository.save(cEntity);

            return new MessageResponse("Category Updated Correctly", "200");
        } catch (Exception e) {
            return new MessageResponse(String.format("Error updating: %s", e.getMessage()), "400");
        }
    }

    @Override
    public MessageResponse deleteCategory(String id) {
        try {
            cRepository.deleteById(id);
            return new MessageResponse(String.format("Category with ID '%s' deleted", id), "200");
        } catch (Exception e) {
            return new MessageResponse(String.format("Error: %s", e.getMessage()), "400");
        }
    }

}

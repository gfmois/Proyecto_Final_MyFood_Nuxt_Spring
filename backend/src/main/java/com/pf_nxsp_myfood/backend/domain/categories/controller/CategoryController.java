package com.pf_nxsp_myfood.backend.domain.categories.controller;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

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

import com.pf_nxsp_myfood.backend.domain.categories.dto.CategoryDto;
import com.pf_nxsp_myfood.backend.domain.categories.service.CategoryService;
import com.pf_nxsp_myfood.backend.domain.payload.request.category.CategoryRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.category.UpdateCategoryRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService cService;

    @Cacheable(value = "categories")
    @GetMapping
    public List<CategoryDto> getCategories() {
        return cService.getCategories();
    }

    @GetMapping("/{id_category}")
    public CategoryDto getCategoryById(@PathVariable String id_category) {
        return cService.getCategoryById(id_category);
    }

    // Create new Category
    @PostMapping
    public MessageResponse createCategory(@RequestBody @Valid CategoryRequest cBody) {
        // New Instance of category DTO
        CategoryDto cDto = new CategoryDto();

        // Transform CategoryRequest to CategoryDto
        cDto.setId_category(IdGenerator.generateWithLength(20));
        cDto.setIcon(cBody.getIcon());
        cDto.setName(cBody.getName());
        cDto.setSlug(cBody.getSlug());

        // Saves Category
        return cService.createCategory(cDto);
    }

    @PutMapping
    public MessageResponse updateCategory(@RequestBody @Valid UpdateCategoryRequest cBody) {
        // New Instance of category DTO
        CategoryDto cDto = new CategoryDto();

        // Transform CategoryRequest to CategoryDto
        cDto.setId_category(cBody.getId_category());
        cDto.setIcon(cBody.getIcon());
        cDto.setName(cBody.getName());
        cDto.setSlug(cBody.getSlug());

        // Updates Category
        return cService.updateCategory(cDto);
    }

    @DeleteMapping("/{id_category}")
    public MessageResponse deleteCategory(@PathVariable String id_category) {
        return cService.deleteCategory(id_category);
    }
 }

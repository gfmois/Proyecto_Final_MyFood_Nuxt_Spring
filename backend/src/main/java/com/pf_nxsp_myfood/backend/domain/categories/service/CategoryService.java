package com.pf_nxsp_myfood.backend.domain.categories.service;

import java.util.List;

import com.pf_nxsp_myfood.backend.domain.categories.dto.CategoryDto;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;

public interface CategoryService {
    List<CategoryDto> getCategories();
    CategoryDto getCategoryById(String id);
    MessageResponse createCategory(CategoryDto cDto);
    MessageResponse updateCategory(CategoryDto cDto);
    MessageResponse deleteCategory(String id);
}

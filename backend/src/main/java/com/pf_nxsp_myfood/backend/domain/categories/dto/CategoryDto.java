package com.pf_nxsp_myfood.backend.domain.categories.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    @NotNull
    private String id_category;
    
    private String slug;

    @NotNull
    private String name;

    @NotNull
    private String icon;

    private List<String> products;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SingleCategory<T> {
        private T category;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MultipleCategories {
        private List<CategoryDto> categories;
    }
}

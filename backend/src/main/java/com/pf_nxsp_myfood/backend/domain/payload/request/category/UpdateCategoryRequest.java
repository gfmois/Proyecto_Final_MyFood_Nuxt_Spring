package com.pf_nxsp_myfood.backend.domain.payload.request.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCategoryRequest {
    @NotBlank
    @NotEmpty
    @NotNull
    private String id_category;

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    @NotBlank
    @NotEmpty
    @NotNull
    private String icon;

    @NotBlank
    @NotEmpty
    @NotNull
    private String slug;
}

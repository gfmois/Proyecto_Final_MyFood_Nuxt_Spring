package com.pf_nxsp_myfood.backend.domain.payload.request.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProductRequest {
    @NotBlank
    @NotNull
    @NotEmpty
    private String id_product;

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotBlank
    @NotNull
    @NotEmpty
    private String slug;

    @NotBlank
    @NotNull
    @NotEmpty
    private String price;

    @NotBlank
    @NotNull
    @NotEmpty
    private String image;
}

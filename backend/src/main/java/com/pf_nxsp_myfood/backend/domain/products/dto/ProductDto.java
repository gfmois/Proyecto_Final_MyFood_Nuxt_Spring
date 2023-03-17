package com.pf_nxsp_myfood.backend.domain.products.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto extends BaseUtils implements Serializable {
    @NotNull
    private String id_product;

    @NotNull
    private String name;
    
    @NotNull
    private String slug;

    @NotNull
    private String price;

    @NotNull
    private String image;

    @NotNull
    private String restaurant;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class SingleProduct<T> {
        private T product;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MultipleProducts {
        private List<ProductDto> products;
    }

}

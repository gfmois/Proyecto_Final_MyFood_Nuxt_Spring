package com.pf_nxsp_myfood.backend.domain.allergens.dto;

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
@Builder
public class AllergenDto {
    @NotNull
    private String id_allergen;

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
    public static class SingleAllergen<T> {
        private T allergen;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MultipleAllergens {
        private List<AllergenDto> allergens;
    }
}

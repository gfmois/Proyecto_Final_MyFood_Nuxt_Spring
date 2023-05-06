package com.pf_nxsp_myfood.backend.domain.reserves.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesStatusTypes;
import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

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
public class ReserveDto extends BaseUtils {
    @NotNull
    @NotEmpty
    private String id_reserve;

    @NotNull
    @NotEmpty
    private String id_client;

    @NotNull
    @NotEmpty
    private String id_restaurant;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private ReservesTypes types;

    @NotNull
    @NotEmpty
    private Date date_reserve;

    @NotNull
    @NotEmpty
    private Integer diners;

    @NotNull
    @NotEmpty
    private ReservesStatusTypes status;
}

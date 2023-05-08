package com.pf_nxsp_myfood.backend.domain.payload.request.reserve;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesStatusTypes;
import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.Getter;

public class UpdateReserveRequest extends BaseUtils {
    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    private String id_reserve;

    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    private ReservesTypes types;

    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    private Integer diners;

    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    private ReservesStatusTypes status;

    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    private Date date_reserve;
}

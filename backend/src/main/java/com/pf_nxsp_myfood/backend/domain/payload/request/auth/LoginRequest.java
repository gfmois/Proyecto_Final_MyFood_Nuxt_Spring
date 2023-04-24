package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

public class LoginRequest extends BaseUtils{
    @NotBlank
    @Getter
    private String email;

    @NotBlank
    @Getter
    private String password;

    @Getter
    private EmployeesTypes type = EmployeesTypes.NONE;
}

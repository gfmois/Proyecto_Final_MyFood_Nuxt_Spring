package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;

public class LoginRequest {
    @NotBlank
    @Getter
    @Setter
    private String email;

    @NotBlank
    @Getter
    @Setter
    private String password;

    @NotBlank
    @Getter
    @Setter
    private EmployeesTypes type = EmployeesTypes.NONE;
}

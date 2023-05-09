package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;

import lombok.Getter;

public class EmployeeUpdateRequest {
    @Getter
    private String id_employee;

    @Getter
    private String name;

    @Getter
    private String avatar;

    @Getter
    private String phone;

    @Getter
    private EmployeesTypes type;

    @Getter
    private String email;
}

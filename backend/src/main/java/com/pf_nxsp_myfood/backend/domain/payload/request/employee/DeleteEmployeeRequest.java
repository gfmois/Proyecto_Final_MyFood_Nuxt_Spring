package com.pf_nxsp_myfood.backend.domain.payload.request.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

public class DeleteEmployeeRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    @Getter
    private String id_employee;
}

package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSignUpRequest extends BaseUtils {
    @NotBlank
    @Size(max = 50)
    @Email
    @Getter
    @Setter
    private String email;

    @NotBlank
    @Size(min = 3, max = 30)
    @Getter
    @Setter
    private String name;

    @NotBlank
    @Size(min = 8, max = 40)
    @Getter
    @Setter
    private String password;

    @NotBlank
    @Size(min = 9, max = 9)
    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    @NotNull
    private EmployeesTypes type = EmployeesTypes.NONE;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    @NotBlank
    private String id_restaurant;
}

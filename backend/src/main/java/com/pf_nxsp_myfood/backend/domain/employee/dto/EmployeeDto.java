package com.pf_nxsp_myfood.backend.domain.employee.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDto extends BaseUtils {
    @NotNull
    @NotBlank
    private String id_employee;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
//    @JsonIgnore
    @Size(min = 8, max = 40)
    private String password;

    @NotNull
    @NotBlank
    private String avatar;

    @NotNull
    @NotBlank
    private EmployeesTypes type;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SingleClient<T> {
        private T employee;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MultipleClients {
        private List<EmployeeDto> employees;
    }
}

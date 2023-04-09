package com.pf_nxsp_myfood.backend.domain.payload.response.auth;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class JWTResponse {
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private String name;
    private String email;
    private String avatar;
    @Builder.Default
    private EmployeesTypes user_type = EmployeesTypes.NONE;

    public JWTResponse(String token, String name, String email, String avatar) {
        this.name = name;
        this.token = token;
        this.email = email;
        this.avatar = avatar;
        this.type = "Bearer";
        this.user_type = EmployeesTypes.NONE;
    }

    public JWTResponse(String token, String name, String email, String avatar, EmployeesTypes user_type) {
        this.name = name;
        this.token = token;
        this.email = email;
        this.avatar = avatar;
        this.type = "Bearer";
        this.user_type = user_type;
    }
}
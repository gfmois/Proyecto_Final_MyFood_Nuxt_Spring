package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
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
}

package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import lombok.Getter;

import javax.validation.constraints.Size;

public class UpdateRequest {
    @Size(min = 3, max = 30)
    @Getter
    private String name;

    @Size(min = 8, max = 40)
    @Getter
    private String password;

    @Size(min = 8, max = 40)
    @Getter
    private String re_password;

    @Size(min = 9, max = 9)
    @Getter
    private String phone;
}

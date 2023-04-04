package com.pf_nxsp_myfood.backend.domain.payload.request.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

public class UpdateRequest {
    @Size(min = 3, max = 30)
    @Getter
    @Setter
    private String name;

    @Size(min = 8, max = 40)
    @Getter
    @Setter
    private String password;

    @Size(min = 9, max = 9)
    @Getter
    @Setter
    private String phone;
}
package com.pf_nxsp_myfood.backend.domain.payload.response.auth;

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
    private String type = "Bearer";
    private String nombre;
    private String email;

    private String avatar;
}

package com.pf_nxsp_myfood.backend.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTUtilsConfiguration {
    @Bean
    public JWTUtils getJwtUtils(
            @Value("${pf_nxsp_myfood.auth.token.sign-key}") String signKey,
            @Value("${pf_nxsp_myfood.auth.token.valid-time}") Long validTime
    ) throws Exception {
        if (signKey.length() < 32) {
            throw new Exception("SignKey must have length at least 32");
        }

        return new JWTUtils(signKey, validTime);
    }
}

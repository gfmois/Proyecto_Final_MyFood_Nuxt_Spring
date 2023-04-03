package com.pf_nxsp_myfood.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String id) {
        return Optional.ofNullable(id)
                .map(userDetailsService::loadUserByUsername)
                .map(clientDetails ->
                        new UsernamePasswordAuthenticationToken(
                                clientDetails,
                                clientDetails.getPassword(),
                                clientDetails.getAuthorities()))
                .orElse(null);
    }
}

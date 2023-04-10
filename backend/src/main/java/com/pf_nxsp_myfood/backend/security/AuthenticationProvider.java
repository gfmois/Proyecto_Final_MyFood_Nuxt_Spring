package com.pf_nxsp_myfood.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.pf_nxsp_myfood.backend.security.service.ClientDetailsServiceImpl;
import com.pf_nxsp_myfood.backend.security.service.EmployeeDetailsServiceImpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {
    @Autowired
    private ClientDetailsServiceImpl clientDetailsServiceImpl;

    @Autowired
    private EmployeeDetailsServiceImpl employeeDetailsServiceImpl;

    public Authentication getAuthentication(Map<String, ?> sub) {
        String userType = (String) sub.get("type");
        String id = (String) sub.get("sub");

        UserDetailsService userDetailsService = userType.equals("NONE") 
            ? clientDetailsServiceImpl 
            : employeeDetailsServiceImpl;

        UserDetails userDetails = Optional.ofNullable(id)
                .map(userDetailsService::loadUserByUsername)
                .orElse(null);
        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } else {
            return null;
        }
    }
}
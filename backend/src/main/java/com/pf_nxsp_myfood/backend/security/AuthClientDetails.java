package com.pf_nxsp_myfood.backend.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthClientDetails implements UserDetails {
    private String id;
    private final String email;
    private final EmployeesTypes type;

    @Builder
    public AuthClientDetails(String id, String email, EmployeesTypes type) {
        this.id = id;
        this.email = email;
        this.type = type;
    }

    @Builder
    public AuthClientDetails(String id_cliente, String email) {
        this.id = id_cliente;
        this.email = email;
        this.type = EmployeesTypes.NONE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword(){
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

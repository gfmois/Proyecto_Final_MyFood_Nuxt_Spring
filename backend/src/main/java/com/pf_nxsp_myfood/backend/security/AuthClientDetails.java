package com.pf_nxsp_myfood.backend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthClientDetails extends BaseUtils implements UserDetails {
    private String id_client;
    private String id_employee;
    private final String email;
    private final EmployeesTypes type;

    public AuthClientDetails(String id_employee, String id_client, String email, EmployeesTypes type) {
        if (type == EmployeesTypes.NONE || type == null) {
            this.id_client = id_client;
        } else {
            this.id_employee = id_employee;
        }

        this.email = email;
        this.type = type;
    }

    public AuthClientDetails(String id_employee, String email, EmployeesTypes type) {
        this(id_employee, null, email, type);
    }
    
    public AuthClientDetails(String id_client, String email) {
        this(null, id_client, email, EmployeesTypes.NONE);
    }
    
    @Builder
    public static AuthClientDetails build(String id_employee, String id_client, String email, EmployeesTypes type) {
        return new AuthClientDetails(id_employee, id_client, email, type);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.type != EmployeesTypes.NONE) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public EmployeesTypes getType() {
        return type;
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

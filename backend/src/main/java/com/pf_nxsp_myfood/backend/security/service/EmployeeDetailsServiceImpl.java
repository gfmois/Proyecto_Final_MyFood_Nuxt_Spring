package com.pf_nxsp_myfood.backend.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.employee.repository.EmployeeRepository;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository eRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return eRepository.findById(email)
                .map(e -> AuthClientDetails.builder().id_employee(e.getId_employee()).email(e.getEmail()).build())
                .orElse(null);
    }

}

package com.pf_nxsp_myfood.backend.security.service;

import com.pf_nxsp_myfood.backend.domain.clients.repository.ClientRepository;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Primary
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository cRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return cRepository.findById(email)
                .map(clientEntity ->
                     AuthClientDetails.builder()
                            .id_client(clientEntity.getId_client())
                            .email(clientEntity.getEmail())
                            .build())
                .orElse(null);
    }
}
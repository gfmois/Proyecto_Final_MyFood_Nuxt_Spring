package com.pf_nxsp_myfood.backend.domain.clients.service;

import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pf_nxsp_myfood.backend.domain.clients.dto.ClientDto;
import com.pf_nxsp_myfood.backend.domain.clients.entity.ClientEntity;
import com.pf_nxsp_myfood.backend.domain.clients.repository.ClientRepository;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.LoginRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.SignUpRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.UpdateRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.auth.JWTResponse;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;
import com.pf_nxsp_myfood.backend.security.jwt.JWTUtils;

import com.pf_nxsp_myfood.backend.exceptions.AppException;
import com.pf_nxsp_myfood.backend.exceptions.Error;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public ClientDto convertEntityToDto(ClientEntity cEntity) {
        return ClientDto.builder()
                .id_client(cEntity.getId_client())
                .name(cEntity.getName())
                .email(cEntity.getEmail())
                .phone(cEntity.getPhone())
                .avatar(cEntity.getAvatar())
                .password(cEntity.getPassword())
                .build();
    }

    public JWTResponse responseToken(ClientEntity entity) {
        return JWTResponse.builder()
                .token(jwtUtils.encode(entity.getId_client(), null))
                .email(entity.getEmail())
                .name(entity.getName())
                .avatar(entity.getAvatar())
                .build();
    }

    @Override
    public JWTResponse registration(final SignUpRequest data) {
        clientRepository.findByNameOrEmail(data.getName(), data.getEmail())
                .stream()
                .findAny()
                .ifPresent(entity -> {
                    throw new AppException(Error.DUPLICATED_USER);
                });

        ClientEntity clientEntity = ClientEntity.builder()
                .id_client(IdGenerator.generateWithLength(10))
                .name(data.getName())
                .email(data.getEmail())
                .password(passwordEncoder.encode(data.getPassword()))
                .phone(data.getPhone())
                .avatar("https://api.multiavatar.com/" + new String(Base64.getEncoder().encode(data.getName().getBytes())) + ".png")
                .build();

        clientRepository.save(clientEntity);
        return responseToken(clientEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public JWTResponse login(LoginRequest credentials) {
        ClientEntity cEntity = clientRepository.findByEmail(credentials.getEmail())
                .filter(client ->
                        passwordEncoder.matches(
                                credentials.getPassword(),
                                client.getPassword()))
                .orElseThrow(() ->
                        new AppException(Error.LOGIN_INFO_INVALID));

        return responseToken(cEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto currentUser(AuthClientDetails client) {
        ClientEntity clientEntity = clientRepository.
                findById(client.getId())
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        return convertEntityToDto(clientEntity);
    }

    @Transactional
    @Override
    public ClientDto update(UpdateRequest newData, final AuthClientDetails clientDetails) {
        ClientEntity cEntity = clientRepository
                .findById(clientDetails.getId())
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        if (newData.getName() != null) {
            cEntity.setName(newData.getName());
        }

        if (newData.getPhone() != null) {
            cEntity.setPhone(newData.getPhone());
        }

        if (newData.getPassword() != null) {
            cEntity.setPassword(passwordEncoder.encode(newData.getPassword()));
        }

        clientRepository.save(cEntity);
        return convertEntityToDto(cEntity);
    }
}
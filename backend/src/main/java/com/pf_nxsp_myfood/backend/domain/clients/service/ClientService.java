package com.pf_nxsp_myfood.backend.domain.clients.service;

import com.pf_nxsp_myfood.backend.domain.clients.dto.ClientDto;

import com.pf_nxsp_myfood.backend.domain.payload.request.auth.SignUpRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.UpdateRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.LoginRequest;

import com.pf_nxsp_myfood.backend.domain.payload.response.auth.JWTResponse;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

public interface ClientService {
    JWTResponse registration(final SignUpRequest data);
    JWTResponse login(final LoginRequest credentials);

    ClientDto currentUser(final AuthClientDetails client);
    ClientDto update(UpdateRequest newData, final AuthClientDetails clientDetails);
}

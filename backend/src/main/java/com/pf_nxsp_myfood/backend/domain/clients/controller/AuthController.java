package com.pf_nxsp_myfood.backend.domain.clients.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.clients.service.ClientService;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.LoginRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.SignUpRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.auth.JWTResponse;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService cService;

    @PostMapping(path = "/register")
    public JWTResponse register(@RequestBody @Valid SignUpRequest data) {
        return cService.registration(data);
    }

    @PostMapping(path = "/login")
    public JWTResponse login(@RequestBody @Valid LoginRequest credentials) {
        return cService.login(credentials);
    }
}

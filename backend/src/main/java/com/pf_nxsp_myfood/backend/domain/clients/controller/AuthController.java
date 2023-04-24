package com.pf_nxsp_myfood.backend.domain.clients.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.clients.service.ClientService;
import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.employee.service.EmployeeService;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.EmployeeSignUpRequest;
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
    private final EmployeeService eService;

    @PostMapping(path = "/register")
    public JWTResponse register(@RequestBody @Valid SignUpRequest data) {
        if (data.getType() != EmployeesTypes.NONE) {
            EmployeeSignUpRequest request = EmployeeSignUpRequest.builder()
                .email(data.getEmail())
                .name(data.getName())
                .id_restaurant(data.getId_restaurant())
                .password(data.getPassword())
                .phone(data.getPhone())
                .type(data.getType())
                .build();

            return eService.registration(request);
        }

        return cService.registration(data);
    }

    @PostMapping(path = "/login")
    public JWTResponse login(@RequestBody @Valid LoginRequest credentials) {
        if (eService.getEmployeeInfo(credentials.getEmail()) != null) {
            return eService.login(credentials);
        }

        return cService.login(credentials);
    }
}

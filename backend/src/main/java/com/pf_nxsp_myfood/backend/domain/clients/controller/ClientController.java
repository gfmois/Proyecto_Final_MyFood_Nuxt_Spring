package com.pf_nxsp_myfood.backend.domain.clients.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.clients.dto.ClientDto;
import com.pf_nxsp_myfood.backend.domain.clients.service.ClientService;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.UpdateRequest;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService cService;

    @GetMapping("/profile")
    public ClientDto getProfile(@AuthenticationPrincipal AuthClientDetails authDetails) {
        return cService.currentUser(authDetails);
    }

    @PutMapping("/profile")
    public ClientDto updateProfile(@AuthenticationPrincipal AuthClientDetails authDetails, @RequestBody @Valid UpdateRequest uRequest) {
        return cService.update(uRequest, authDetails);
    }
}

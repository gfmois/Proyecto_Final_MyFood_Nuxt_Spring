package com.pf_nxsp_myfood.backend.domain.reserves.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.payload.request.reserve.ReserveRequest;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reserves")
public class ReservesController {
    @PostMapping
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal AuthClientDetails aDetails, @RequestBody ReserveRequest reserve) {
        return null;
    }
}

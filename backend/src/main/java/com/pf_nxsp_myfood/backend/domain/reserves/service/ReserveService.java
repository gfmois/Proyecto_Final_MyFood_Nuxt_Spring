package com.pf_nxsp_myfood.backend.domain.reserves.service;

import org.springframework.http.ResponseEntity;

import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;

public interface ReserveService {
    ResponseEntity<?> createOrder(ReserveDto reserve);
}

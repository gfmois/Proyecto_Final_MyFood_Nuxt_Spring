package com.pf_nxsp_myfood.backend.domain.reserves.service;

import org.springframework.http.ResponseEntity;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;

public interface ReserveService {
    ResponseEntity<?> createOrder(ReserveDto reserve);
    ResponseEntity<?> getBannedDays(Integer diners, ReservesTypes type, String id_restaurant);
}

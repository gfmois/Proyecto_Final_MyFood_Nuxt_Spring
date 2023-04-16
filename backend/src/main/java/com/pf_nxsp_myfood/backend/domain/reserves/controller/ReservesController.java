package com.pf_nxsp_myfood.backend.domain.reserves.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.payload.request.reserve.ReserveRequest;
import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;
import com.pf_nxsp_myfood.backend.domain.reserves.service.ReserveService;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reserves")
public class ReservesController {
    @Autowired
    private ReserveService rService;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableRestaurants(@RequestParam Integer diners, @RequestParam ReservesTypes type, @RequestParam String id_restaurant) {
        return rService.getBannedDays(diners, type, id_restaurant);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal AuthClientDetails aDetails, @RequestBody ReserveRequest reserve) {
        new ReserveDto();
        ReserveDto dto = ReserveDto.builder()
            .id_reserve(IdGenerator.generateWithLength(20))
            .id_client(aDetails.getId_client())
            .id_restaurant(reserve.getId_restaurant())
            .diners(reserve.getDiners())
            .date_reserve(reserve.getDate_reserve())
            .types(reserve.getType())
            .status(reserve.getStatus())
            .build();
                
            return rService.createOrder(dto);
    }
}

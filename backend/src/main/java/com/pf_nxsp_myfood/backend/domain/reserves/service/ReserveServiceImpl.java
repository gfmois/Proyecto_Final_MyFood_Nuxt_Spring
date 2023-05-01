package com.pf_nxsp_myfood.backend.domain.reserves.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.clients.entity.ClientEntity;
import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;
import com.pf_nxsp_myfood.backend.domain.reserves.entity.ReserveEntity;
import com.pf_nxsp_myfood.backend.domain.reserves.repository.ReserveRepository;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    private ReserveEntity convertDtoToEntity(ReserveDto dto) {
        return ReserveEntity.builder()
                .id_reserve(dto.getId_reserve())
                .diners(dto.getDiners())
                .date_reserve(dto.getDate_reserve())
                .types(dto.getTypes())
                .restaurant(RestaurantEntity.builder().id_restaurant(dto.getId_restaurant()).build())
                .client(ClientEntity.builder().id_client(dto.getId_client()).build())
                .status(dto.getStatus())
                .name(dto.getName())
                .build();

    }

    @Override
    public ResponseEntity<Map<String, Object>> createOrder(ReserveDto reserve) {
        Map<String, Object> response = new HashMap<String, Object>();

        if (reserveRepository.save(convertDtoToEntity(reserve)) != null) {
            response.put("id_reserve", reserve.getId_reserve());
            response.put("status", 200);
            response.put("message", "Reserve created correctly");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", 400);
        response.put("message", "Error while trying save the reserve");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getBannedDays(Integer diners, ReservesTypes type, String id_restaurant) {
        List<Object> finalList = new ArrayList<>();

        finalList.addAll(reserveRepository.getHolidays(id_restaurant));
        finalList.addAll(reserveRepository.getBannedDays(diners, String.valueOf(type.ordinal()), id_restaurant));

        return new ResponseEntity<List<Object>>(finalList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getHolidays(String id_restaurant) {
        List<Object> response = new ArrayList<>();
        response.addAll(reserveRepository.getHolidays(id_restaurant));
        return new ResponseEntity<List<Object>>(response, HttpStatus.OK);
    }

}

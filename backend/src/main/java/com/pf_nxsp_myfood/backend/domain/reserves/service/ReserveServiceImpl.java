package com.pf_nxsp_myfood.backend.domain.reserves.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.clients.entity.ClientEntity;
import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;
import com.pf_nxsp_myfood.backend.domain.reserves.entity.ReserveEntity;
import com.pf_nxsp_myfood.backend.domain.reserves.repository.ReserveRepository;
import com.pf_nxsp_myfood.backend.domain.restaurants.dto.RestaurantDto;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;
import com.pf_nxsp_myfood.backend.domain.restaurants.service.RestaurantSerivce;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private RestaurantSerivce rSerivce;

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

    private ReserveDto convertToDto(ReserveEntity entity) {
        return ReserveDto.builder()
                .id_reserve(entity.getId_reserve())
                .diners(entity.getDiners())
                .date_reserve(entity.getDate_reserve())
                .types(entity.getTypes())
                .id_client(entity.getClient_reserves().getId_client())
                .id_restaurant(entity.getRestaurant_reserves().getId_restaurant())
                .name(entity.getName())
                .status(entity.getStatus())
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

    @Override
    public List<ReserveDto> getRestaurantReservesByEmployee(String id_employee) {
        String idRestaurant = rSerivce.getRestaurantByEmployee(id_employee).getId_restaurant();
        List<ReserveDto> reserves = reserveRepository.findByIdRestaurant(idRestaurant)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return reserves;
    }

    @Override
    public ReserveDto getReserve(String id_Order) {
        return convertToDto(reserveRepository.findById(id_Order).get());
    }

    @Override
    public ResponseEntity<?> updateReserve(ReserveDto reserve) {
        try {
            ReserveEntity reserveToUpdate = reserveRepository.findById(reserve.getId_reserve()).get();

            reserveToUpdate.setStatus(reserve.getStatus());
            reserveToUpdate.setDate_reserve(reserve.getDate_reserve());
            reserveToUpdate.setDiners(reserve.getDiners());
            reserveToUpdate.setTypes(reserve.getTypes());

            if (reserveRepository.save(reserveToUpdate) != null) {
                return ResponseEntity.ok().body(Map.of("Status", 200, "message", "Reserve Updated"));
            }

            return ResponseEntity.badRequest()
                    .body(Map.of("Status", 400, "message", "Error while trying to update the Reserve"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("Status", 400, "message", e.getMessage()));
        }
    }

    @Override
    public List<Object> getClientReserves(String id_client) {
        // FIXME: Non Sense what i'm doing here
        Map<String, Object> restaurants = new HashMap<>();
        Map<String, Object> reserves = new HashMap<>();
        List<Map<String, Object>> reservesFilter = reserveRepository.findAll().stream()
                .filter(e -> e.getClient_reserves().getId_client().equals(id_client)).map(this::convertToDto)
                .map(e -> {
                    restaurants.put(e.getId_reserve(), ((RestaurantDto) rSerivce.getRestaurantById(e.getId_restaurant()).get("restaurant")).getName());
                    Map<String, Object> item = new HashMap<String, Object>();

                    item.put(e.getId_reserve(), e);

                    return item;
                })
                .collect(Collectors.toList());
        return null;
    }

}

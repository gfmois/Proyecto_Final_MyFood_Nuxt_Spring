package com.pf_nxsp_myfood.backend.domain.reserves.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.payload.request.reserve.UpdateClientReserveRequest;
import com.pf_nxsp_myfood.backend.domain.reserves.dto.ReserveDto;

public interface ReserveService {
    ResponseEntity<?> createOrder(ReserveDto reserve);
    ResponseEntity<?> getBannedDays(Integer diners, ReservesTypes type, String id_restaurant);
    ResponseEntity<?> getHolidays(String id_restaurant);
    List<ReserveDto> getRestaurantReservesByEmployee(String id_employee);
    ReserveDto getReserve(String id_Order);
    ResponseEntity<?> updateReserve(ReserveDto reserve);
    List<Map<String, Object>> getClientReserves(String id_client);
    ReserveDto updateClientReserve(String id_client, UpdateClientReserveRequest request);
}


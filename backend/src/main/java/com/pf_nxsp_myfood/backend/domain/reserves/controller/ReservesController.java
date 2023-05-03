package com.pf_nxsp_myfood.backend.domain.reserves.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.employee.service.EmployeeService;
import com.pf_nxsp_myfood.backend.domain.payload.request.reserve.ReserveRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.reserve.UpdateReserveRequest;
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

    @Autowired
    private EmployeeService eService;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableRestaurants(@RequestParam String diners, @RequestParam String types,
            @RequestParam String id_restaurant) {
        return rService.getBannedDays(Integer.valueOf(diners), ReservesTypes.valueOf(types), id_restaurant);
    }

    @GetMapping("/holidays")
    public ResponseEntity<?> getHolidays(@RequestParam String restaurant) {
        return rService.getHolidays(restaurant);
    }

    @GetMapping("/restaurant")
    public Map<String, Object> getRestaurantReserves(@AuthenticationPrincipal AuthClientDetails aDetails) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            Map<String, Object> err = new HashMap<String, Object>();

            err.put("status", 400);
            err.put("message", "No ID Found");

            return err;
        }

        List<ReserveDto> reserves = rService.getRestaurantReservesByEmployee(aDetails.getId_employee());

        Map<String, Object> res = new HashMap<String, Object>();

        res.put("reserves", reserves);
        res.put("status", 200);

        return res;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody ReserveRequest reserve) {
        ReserveDto dto = ReserveDto.builder()
                .id_reserve(IdGenerator.generateWithLength(20))
                .id_client(aDetails.getId_client())
                .id_restaurant(reserve.getId_restaurant())
                .diners(reserve.getDiners())
                .date_reserve(reserve.getDate_reserve())
                .types(reserve.getType())
                .status("Reserved")
                .name(reserve.getName())
                .build();

        return rService.createOrder(dto);
    }

    @PutMapping
    public ResponseEntity<?> updateReserve(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody UpdateReserveRequest request) {

                System.out.println(request.toString());

        if (aDetails == null || aDetails.getId_employee() == null) {
            Map<String, Object> err = new HashMap<String, Object>();

            err.put("status", 400);
            err.put("message", "No ID Found");

            return ResponseEntity.badRequest().body(err);
        }

        if (eService.isEmployee(rService.getReserve(request.getId_reserve()).getId_restaurant(),
                aDetails.getId_employee())) {
            ReserveDto reserve = ReserveDto.builder()
                    .id_reserve(request
                    .getId_reserve())
                    .diners(request.getDiners())
                    .status(request.getStatus())
                    .types(request.getTypes())
                    .date_reserve(request.getDate_reserve())
                    .build();

            return rService.updateReserve(reserve);
        }

        return ResponseEntity.badRequest().body(Map.of("Status", 200, "message", "Error trying to update the Reserve"));
    }
}

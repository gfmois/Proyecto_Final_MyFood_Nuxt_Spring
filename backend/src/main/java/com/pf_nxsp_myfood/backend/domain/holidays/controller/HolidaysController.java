package com.pf_nxsp_myfood.backend.domain.holidays.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.employee.dto.EmployeeDto;
import com.pf_nxsp_myfood.backend.domain.employee.service.EmployeeService;
import com.pf_nxsp_myfood.backend.domain.holidays.dto.HolidayDto;
import com.pf_nxsp_myfood.backend.domain.holidays.service.HolidaysService;
import com.pf_nxsp_myfood.backend.domain.payload.request.holidays.request.HolidayRequest;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/holidays")
public class HolidaysController {
    @Autowired
    private HolidaysService hService;

    @Autowired
    private EmployeeService eService;

    @GetMapping
    public ResponseEntity<?> getRestaurantHolidays(@RequestParam String id_restaurant) {
        try {
            List<HolidayDto> holidays = hService.getRestaurantHolidays(id_restaurant);
            if (holidays.size() > 0) {
                return ResponseEntity.ok().body(Map.of("status", 200, "holidays", holidays));
            }

            return ResponseEntity.ok().body(Map.of("status", 200, "message", "No Holidays"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRestaurantHoliday(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody Map<String, Object> body) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Finded"));
        }

        EmployeeDto employee = eService.currentUser(aDetails);

        if (employee.getType() == EmployeesTypes.ADMIN) {
            HolidayDto h = hService.getHoliday((String) body.get("id_holiday"));
            if (employee.getId_restaurant().equals(h.getRestaurant())) {
                if (hService.deleteHoliday(h.getId_holiday())) {
                    return ResponseEntity.ok().body(Map.of("status", 200, "message", "Holiday deleted correctly"));
                }
            }

            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "Error while trying to remove an holiday"));

        }

        return ResponseEntity.badRequest()
                .body(Map.of("status", 400, "message", "Error while trying to remove an holiday"));
    }

    @PutMapping
    public ResponseEntity<?> updateHoliday(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody Map<String, Object> body) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Finded"));
        }

        EmployeeDto employee = eService.currentUser(aDetails);

        if (employee.getType() == EmployeesTypes.ADMIN) {
            HolidayDto h = hService.getHoliday((String) body.get("id_holiday"));

            h.setDescription((String) body.get("description"));

            LocalDate date = LocalDate.parse((String) body.get("holiday_date"));
            h.setHoliday_date(Date.valueOf(date));

            if (employee.getId_restaurant().equals(h.getRestaurant())) {
                HolidayDto result = hService.updateHoliday(h);
                if (result != null) {
                    return ResponseEntity.ok()
                            .body(Map.of("status", 200, "message", "Holiday updated correctly", "holiday", result));
                }
            }

            return ResponseEntity.badRequest()
                    .body(Map.of("status", 400, "message", "Error while trying to update an holiday"));
        }

        return ResponseEntity.badRequest()
                .body(Map.of("status", 400, "message", "Error while trying to update an holiday"));
    }

    @PostMapping
    public ResponseEntity<?> createHoliday(@AuthenticationPrincipal AuthClientDetails aDetails, @RequestBody HolidayRequest body) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Finded"));
        }

        EmployeeDto employee = eService.currentUser(aDetails);

        if (employee.getType() == EmployeesTypes.ADMIN) {
            HolidayDto dto = HolidayDto.builder()
                .id_holiday(IdGenerator.generateWithLength(20))
                .description(body.getDescription())
                .holiday_date(body.getHoliday_date())
                .restaurant(employee.getId_restaurant())
                .build();

            return hService.createHoliday(dto);
        }

        return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "Error while trying to create the Holiday"));
    }
}

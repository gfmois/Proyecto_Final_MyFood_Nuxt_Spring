package com.pf_nxsp_myfood.backend.domain.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;
import com.pf_nxsp_myfood.backend.domain.employee.dto.EmployeeDto;
import com.pf_nxsp_myfood.backend.domain.employee.service.EmployeeService;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.EmployeeUpdateRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.employee.DeleteEmployeeRequest;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService eService;

    @GetMapping("/admin")
    public EmployeeDto isAdmin(@AuthenticationPrincipal AuthClientDetails aDetails) {
        return eService.currentUser(aDetails);
    }

    @GetMapping("/profile")
    public EmployeeDto getProfile(@AuthenticationPrincipal AuthClientDetails authDetails) {
        return eService.currentUser(authDetails);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<?> getRestaurantEmployees(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestParam("id_restaurant") String id_restaurant) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        if (eService.isEmployee(id_restaurant, aDetails.getId_employee())) {

            List<EmployeeDto> employees = eService.getRestaurantEmployees(id_restaurant);
            return ResponseEntity.ok().body(Map.of("status", 200, "employees", employees));

        }
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEmployee(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody DeleteEmployeeRequest body) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        EmployeeDto admin = eService.currentUser(aDetails);

        if (!admin.getType().equals(EmployeesTypes.ADMIN)) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "Sin permisos suficientes"));
        } else {
            return eService.deleteEmployee(body.getId_employee());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@AuthenticationPrincipal AuthClientDetails aDetails,
            @RequestBody EmployeeUpdateRequest request) {
        if (aDetails == null || aDetails.getId_employee() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "No ID Found"));
        }

        EmployeeDto admin = eService.currentUser(aDetails);

        if (!admin.getType().equals(EmployeesTypes.ADMIN)) {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "Sin permisos suficientes"));
        } else {
            return eService.updateEmployee(request);
        }
    }

}

package com.pf_nxsp_myfood.backend.domain.employee.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pf_nxsp_myfood.backend.domain.employee.dto.EmployeeDto;
import com.pf_nxsp_myfood.backend.domain.employee.service.EmployeeService;
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
        System.out.print(aDetails);
        // return eService.currentUser(aDetails);
        return null;
    }
}

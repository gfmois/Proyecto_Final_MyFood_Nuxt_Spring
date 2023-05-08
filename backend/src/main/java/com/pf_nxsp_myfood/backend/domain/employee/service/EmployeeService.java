package com.pf_nxsp_myfood.backend.domain.employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pf_nxsp_myfood.backend.domain.employee.dto.EmployeeDto;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.EmployeeSignUpRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.LoginRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.UpdateRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.auth.JWTResponse;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;

public interface EmployeeService {
    JWTResponse registration(final EmployeeSignUpRequest data);
    JWTResponse login(final LoginRequest credentials);

    EmployeeDto currentUser(final AuthClientDetails client);
    EmployeeDto update(UpdateRequest newData, final AuthClientDetails clientDetails);
    EmployeeDto getEmployeeInfo(String email);

    Boolean isEmployee(String id_restaurant, String id_employee);
    List<EmployeeDto> getRestaurantEmployees(String id_restaurant);
    ResponseEntity<?> deleteEmployee(String id_employee);
}

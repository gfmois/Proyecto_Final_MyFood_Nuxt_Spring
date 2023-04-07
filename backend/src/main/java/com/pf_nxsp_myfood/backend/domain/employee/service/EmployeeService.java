package com.pf_nxsp_myfood.backend.domain.employee.service;

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
}

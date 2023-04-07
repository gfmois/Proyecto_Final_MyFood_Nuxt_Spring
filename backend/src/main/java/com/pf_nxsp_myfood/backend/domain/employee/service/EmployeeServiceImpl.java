package com.pf_nxsp_myfood.backend.domain.employee.service;

import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.employee.dto.EmployeeDto;
import com.pf_nxsp_myfood.backend.domain.employee.entity.EmployeeEntity;
import com.pf_nxsp_myfood.backend.domain.employee.repository.EmployeeRepository;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.EmployeeSignUpRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.LoginRequest;
import com.pf_nxsp_myfood.backend.domain.payload.request.auth.UpdateRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.auth.JWTResponse;
import com.pf_nxsp_myfood.backend.exceptions.AppException;
import com.pf_nxsp_myfood.backend.exceptions.Error;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;
import com.pf_nxsp_myfood.backend.security.AuthClientDetails;
import com.pf_nxsp_myfood.backend.security.jwt.JWTUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository eRepository;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder pEncoder;

    public EmployeeDto convertEntityToDTO(EmployeeEntity entity) {
        return EmployeeDto.builder()
            .id_employee(entity.getId_employee())
            .name(entity.getName())
            .email(entity.getEmail())
            .phone(entity.getPhone())
            .avatar(entity.getAvatar())
            .password(entity.getPassword())
            .type(entity.getType())
            .build();
    }

    public JWTResponse responseToken(EmployeeEntity entity) {
        return JWTResponse.builder()
            .token(jwtUtils.encode(entity.getId_employee()))
            .email(entity.getEmail())
            .name(entity.getName())
            .avatar(entity.getAvatar())
            .user_type(entity.getType())
            .build();
    }

    @Override
    public JWTResponse registration(EmployeeSignUpRequest data) {
        eRepository.findByNameOrEmail(data.getName(), data.getEmail())
            .stream()
            .findAny()
            .ifPresent(entity -> {
                throw new AppException(Error.DUPLICATED_USER);
            });

            System.out.println(data.toString());

        EmployeeEntity eEntity = EmployeeEntity.builder()
            .id_employee(IdGenerator.generateWithLength(20))
            .name(data.getName())
            .email(data.getEmail())
            .password(data.getPassword())
            .phone(data.getPhone())
            .avatar(String.format("https://api.multiavatar.com/%s.png", new String(Base64.getEncoder().encode(data.getName().getBytes()))))
            .type(data.getType())
            .empl_restaurant(data.getId_restaurant())
            .build();

        eRepository.save(eEntity);
        return responseToken(eEntity);
    }

    @Override
    public JWTResponse login(LoginRequest credentials) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public EmployeeDto currentUser(AuthClientDetails client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'currentUser'");
    }

    @Override
    public EmployeeDto update(UpdateRequest newData, AuthClientDetails clientDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}

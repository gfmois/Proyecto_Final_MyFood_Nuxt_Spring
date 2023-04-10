package com.pf_nxsp_myfood.backend.domain.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pf_nxsp_myfood.backend.domain.employee.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    Optional<EmployeeEntity> findByName(String name);

    @Override
    @Query("SELECT c FROM EmployeeEntity c WHERE c.id_employee = :id_employee")
    Optional<EmployeeEntity> findById(@Param("id_employee") String id_employee);

    @Query("SELECT c FROM EmployeeEntity c WHERE c.email = :email")
    Optional<EmployeeEntity> findByEmail(String email);

    @Query("SELECT c FROM EmployeeEntity c WHERE c.name = :name OR c.email = :email")
    List<EmployeeEntity> findByNameOrEmail(@Param("name") String name, @Param("email") String email);
}

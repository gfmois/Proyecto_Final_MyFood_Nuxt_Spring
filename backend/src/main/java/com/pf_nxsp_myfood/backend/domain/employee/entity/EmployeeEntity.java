package com.pf_nxsp_myfood.backend.domain.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;
import com.pf_nxsp_myfood.backend.domain.common.constants.EmployeesTypes;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
    name = "employees",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = "name"),
            @UniqueConstraint(columnNames = "email")
})
@NamedEntityGraph(name = "fetch-employees")
public class EmployeeEntity extends BaseUtils {
    @Id
    private String id_employee;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private EmployeesTypes type;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity employee_restaurant;

    @Builder
    public EmployeeEntity(String id_employee, String name, String email, String phone, String password, String avatar, RestaurantEntity employee_restaurant, EmployeesTypes type) {
        this.id_employee = id_employee;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
        this.type = type;

        this.employee_restaurant = employee_restaurant;
    }
}
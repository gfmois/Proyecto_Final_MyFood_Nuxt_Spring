package com.pf_nxsp_myfood.backend.domain.clients.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;
import com.pf_nxsp_myfood.backend.domain.reserves.entity.ReserveEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
    name = "clients",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = "email")
})
@NamedEntityGraph(name = "fetch-clients")
public class ClientEntity extends BaseUtils {
    @Id
    private String id_client;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "client_reserves")
    private List<ReserveEntity> reserves;

    @Builder
    public ClientEntity(String id_client, String name, String email, String phone, String password, String avatar) {
        this.id_client = id_client;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;

        this.reserves = new ArrayList<>();
    }
}

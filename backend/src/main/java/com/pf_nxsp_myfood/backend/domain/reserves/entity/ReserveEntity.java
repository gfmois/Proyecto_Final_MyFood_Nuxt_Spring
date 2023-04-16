package com.pf_nxsp_myfood.backend.domain.reserves.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.common.constants.ReservesTypes;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reserves")
@NamedEntityGraph(name = "fetch-reserves")
public class ReserveEntity {
    @Id
    private String id_reserve;

    @Column(name = "date_reserve")
    private Date date_reserve;

    @Column(name = "type")
    private ReservesTypes types;

    @Column(name = "diners")
    private Integer diners;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant_reserves;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private RestaurantEntity client_reserves;
}

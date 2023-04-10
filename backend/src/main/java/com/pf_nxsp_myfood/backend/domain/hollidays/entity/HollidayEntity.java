package com.pf_nxsp_myfood.backend.domain.hollidays.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hollidays")
@NamedEntityGraph(name = "fetch-hollidays")
public class HollidayEntity {
    @Id
    private String id_holliday;

    @Column(name = "holliday_date")
    private Date holliday_date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant_hollidays;
}

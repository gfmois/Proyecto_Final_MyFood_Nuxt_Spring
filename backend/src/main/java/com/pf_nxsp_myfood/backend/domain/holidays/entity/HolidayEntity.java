package com.pf_nxsp_myfood.backend.domain.holidays.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "holidays")
@NamedEntityGraph(name = "fetch-holidays")
@AllArgsConstructor
@Builder
public class HolidayEntity extends BaseUtils {
    @Id
    private String id_holiday;

    @Column(name = "holiday_date")
    private Date holiday_date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant_holidays;
}

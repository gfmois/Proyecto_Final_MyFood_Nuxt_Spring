package com.pf_nxsp_myfood.backend.domain.orders.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.common.constants.OrderTypes;
import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;
import com.pf_nxsp_myfood.backend.domain.restaurants.entity.RestaurantEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@NamedEntityGraph(name = "fetch-orders")
public class OrderEntity extends BaseUtils implements Serializable {
    @Id
    private String id_order;

    @Column(name = "id_client")
    private String id_client;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "status")
    private OrderTypes status;

    @OneToMany(
        mappedBy = "orders",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    private Set<OrderProductEntity> products_ordered;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private RestaurantEntity restaurant_orders;

    @Builder
    public OrderEntity(String id_order, String id_client, String id_restaurant, LocalDate orderDate, OrderTypes status, RestaurantEntity restaurant) {
        this.id_order = id_order;
        this.id_client = id_client;
        this.orderDate = orderDate;
        this.status = status;
        this.restaurant_orders = restaurant;
    }
}

package com.pf_nxsp_myfood.backend.domain.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products_ordered")
@IdClass(OrderProductId.class)
public class OrderProductEntity {
    @Id
    @Column(name = "id_order")
    private String id_order;
    
    @Id
    @Column(name = "id_product")
    private String id_product;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private OrderEntity orders;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private ProductEntity products;

    @Builder
    public OrderProductEntity(String id_order, String id_product, Integer quantity) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.quantity = quantity;
    }


}

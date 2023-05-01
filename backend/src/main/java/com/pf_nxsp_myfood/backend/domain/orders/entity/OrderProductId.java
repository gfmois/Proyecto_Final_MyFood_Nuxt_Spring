package com.pf_nxsp_myfood.backend.domain.orders.entity;

import java.io.Serializable;
import java.util.Objects;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductId extends BaseUtils implements Serializable {
    private String id_order;
    private String id_product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProductId that = (OrderProductId) o;
        return Objects.equals(getId_order(), that.getId_order()) &&
               Objects.equals(getId_product(), that.getId_product());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_order(), getId_product());
    }
}

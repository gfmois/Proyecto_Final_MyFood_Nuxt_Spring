package com.pf_nxsp_myfood.backend.domain.reserves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pf_nxsp_myfood.backend.domain.reserves.entity.ReserveEntity;

public interface ReserveRepository extends JpaRepository<ReserveEntity, String> {
    @Query(value = "SELECT holiday_date FROM holidays WHERE id_restaurant = :restaurant", nativeQuery = true)
    public List<?> getHolidays(@Param("restaurant") String restaurant);

    @Query(value = "SELECT r.date_reserve FROM reserves r, restaurants rr WHERE r.id_restaurant = :restaurant AND r.id_restaurant = rr.id_restaurant GROUP BY r.date_reserve, r.type, rr.capacity HAVING (SUM(r.diners) + :diners) > rr.capacity AND r.type = :type", nativeQuery = true)
    public List<?> getBannedDays(@Param("diners") Integer diners, @Param("type") String type, @Param("restaurant") String restaurant);

    @Query(value = "SELECT r FROM ReserveEntity r WHERE r.restaurant_reserves.id_restaurant = :id_restaurant")
    List<ReserveEntity> findByIdRestaurant(@Param("id_restaurant") String id_restaurant);
}

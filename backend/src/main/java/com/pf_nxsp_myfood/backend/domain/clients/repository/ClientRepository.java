package com.pf_nxsp_myfood.backend.domain.clients.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pf_nxsp_myfood.backend.domain.clients.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByName(String name);

    @Override
    @Query("SELECT c FROM ClientEntity c WHERE c.id_client = :id_client")
    Optional<ClientEntity> findById(@Param("id_client") String id_client);

    Optional<ClientEntity> findByEmail(String email);
    @Query("SELECT c FROM ClientEntity c WHERE c.name = :name OR c.email = :email")
    List<ClientEntity> findByNameOrEmail(@Param("name") String name, @Param("email") String email);

}
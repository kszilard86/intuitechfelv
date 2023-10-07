package com.example.feladat.repository;

import com.example.feladat.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Object> findByEmail(String email);

    @Query("select c from Client c where c.id = :id")
    Optional<Client> findById(@Param("id") String id);
}

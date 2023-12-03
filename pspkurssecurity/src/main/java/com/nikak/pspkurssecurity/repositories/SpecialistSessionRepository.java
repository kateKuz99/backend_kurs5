package com.nikak.pspkurssecurity.repositories;

import com.nikak.pspkurssecurity.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecialistSessionRepository extends JpaRepository<Session, Long> {

    @Query("select t from Session  t where t.specialist.id = ?1")
    List<Session> findBySpecialistId(Long specialistId);

    @Query("select t from Session  t where t.client.id = ?1")
    List<Session> findByClientId(Long clientId);
}
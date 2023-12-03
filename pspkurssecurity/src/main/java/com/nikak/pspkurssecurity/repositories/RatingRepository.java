package com.nikak.pspkurssecurity.repositories;

import com.nikak.pspkurssecurity.entities.Rating;
import com.nikak.pspkurssecurity.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT r FROM Rating r where r.client.id = ?1 and r.specialist.id = ?2")
    Optional<Rating> findByClientIdAndSpecialistId(Long clientId, Long specialistId);

    @Query("SELECT r FROM Rating  r where r.id = ?1 and r.client.id = ?2")
    Optional<Rating> findByIdAndClientId(Long id, Long studentId);

    @Query("SELECT r, r.specialist FROM Rating  r where r.client.id = ?1")
    List<Object[]> findByClientId(Long clientId);
}

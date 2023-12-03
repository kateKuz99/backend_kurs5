package com.nikak.pspkurssecurity.repositories;

import com.nikak.pspkurssecurity.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    @Query("SELECT c FROM Certificate c WHERE c.fileName = ?1")
    Optional<Certificate> findCertificateByFileName(String fileName);

    @Query("SELECT c FROM Certificate c WHERE c.id = ?1 and c.specialist.id = ?2")
    Optional<Certificate> findByIdAndSpecialist(Long certificateId, Long teacherId);
}
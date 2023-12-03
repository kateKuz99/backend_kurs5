package com.nikak.pspkurssecurity.repositories;

import com.nikak.pspkurssecurity.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SpecialistRepository
        extends JpaRepository<Specialist, Long> {

    @Query(value = "SELECT specialist.*" +
            " from specialist " +
            " inner join specialist_favor" +
            " on specialist.id = specialist_favor.specialist_id\n" +
            " where favor_id = ?1",
            nativeQuery = true)
    Optional<List<Specialist>> findSpecialistsByFavorId(Long favor_id);

    @Query("SELECT s FROM Specialist s where s.id = ?1")
    Optional<Specialist> findById(Long specialistId);

    @Query("SELECT s from Specialist s inner join s.specialistFavors f where f.id = ?1")
    List<Specialist> findByFavorId(Long favorId);
    @Query("SELECT t FROM Specialist t WHERE t.filename = ?1")
    Optional<Specialist> findSpecialistByFileName(String fileName);


}

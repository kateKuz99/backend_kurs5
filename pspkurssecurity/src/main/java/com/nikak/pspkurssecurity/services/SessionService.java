package com.nikak.pspkurssecurity.services;

import com.nikak.pspkurssecurity.dto.SessionRequest;
import com.nikak.pspkurssecurity.entities.Session;
import com.nikak.pspkurssecurity.entities.Time;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SessionService {
    String addNewSession(SessionRequest sessionRequest, String email);
    Optional<Session> findSessionById(Long id);

    Optional<List<Session>> findSessionsBySpecialistId(Long id);

    Optional<List<Time>> findFreeTime(Date date, Long specialistId);

    String deleteSession(Long id) throws IOException;

    List<Session> getSessions();
}

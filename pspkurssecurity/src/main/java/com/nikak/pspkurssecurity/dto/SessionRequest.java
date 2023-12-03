package com.nikak.pspkurssecurity.dto;

import com.nikak.pspkurssecurity.entities.Payment;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class SessionRequest {
    private Long specialistId;
    private Long favorId;
    private Payment payment;
    private LocalDate sessionDate;
    private Long timeId;
    private Long clientId;
}

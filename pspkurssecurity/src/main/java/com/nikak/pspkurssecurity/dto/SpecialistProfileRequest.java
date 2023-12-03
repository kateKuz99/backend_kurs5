package com.nikak.pspkurssecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SpecialistProfileRequest {
    private String name;
    private String info;
    private Set<Long> favorsIds;
}

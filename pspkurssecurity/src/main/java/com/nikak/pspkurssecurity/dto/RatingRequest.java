package com.nikak.pspkurssecurity.dto;


import lombok.Data;

@Data
public class RatingRequest {
    private Long specialistId;
    private Integer rating;
    private String comment;
}

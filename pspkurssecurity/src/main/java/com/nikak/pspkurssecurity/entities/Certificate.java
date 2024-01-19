package com.nikak.pspkurssecurity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private  String fileName;
    @OneToOne
    @JoinColumn(name="specialist_id", nullable=false)
    @JsonBackReference
    private Specialist specialist;

    public Certificate(String fileName, Specialist specialist) {
        this.fileName = fileName;
        this.specialist = specialist;
    }

    public Certificate() {
    }

    public Certificate(Long id, String fileName, Specialist specialist) {
        this.id = id;
        this.fileName = fileName;
        this.specialist = specialist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
}

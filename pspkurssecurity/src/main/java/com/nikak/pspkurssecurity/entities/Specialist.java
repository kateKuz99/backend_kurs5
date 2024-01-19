package com.nikak.pspkurssecurity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;

import static java.util.Arrays.stream;

@Entity
public class Specialist {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String info;
    private String filename;
    @Transient
    private Double finalRating;



    @ManyToMany()
    @JoinTable(
            name = "specialist_favor",
            joinColumns =  @JoinColumn(name = "specialist_id", referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name = "favor_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<Favor> specialistFavors = new HashSet<>();

    @OneToOne(mappedBy="specialist")
    @JsonManagedReference
    private Certificate certificate;

    @OneToMany(mappedBy="specialist")
    @JsonBackReference
    private List<Session> sessions;

    @OneToMany(mappedBy="specialist")
    @JsonManagedReference
    private List<Rating> specialistRating;

    public Specialist(){}


    public Specialist(Long id, String name, String info, String filename, Double finalRating, Set<Favor> specialistFavors, Certificate certificate, List<Session> sessions, List<Rating> specialistRating) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.filename = filename;
        this.finalRating = finalRating;
        this.specialistFavors = specialistFavors;
        this.certificate = certificate;
        this.sessions = sessions;
        this.specialistRating = specialistRating;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void setSpecialistRating(List<Rating> specialistRating) {
        this.specialistRating = specialistRating;
    }

    public void setFinalRating() {
        this.finalRating = 0.0;
    }

    public Double getFinalRating() {
        return specialistRating.stream().map(Rating::getRating).map(Double::valueOf)
                .reduce(Double::sum).map(it->it/specialistRating.size())
                .orElse(0.0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Set<Favor> getSpecialistFavors() {
        return specialistFavors;
    }

    public void setSpecialistFavors(Set<Favor> specialistFavors) {
        this.specialistFavors = specialistFavors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialist that = (Specialist) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(info, that.info) && Objects.equals(filename, that.filename) && Objects.equals(finalRating, that.finalRating) && Objects.equals(specialistFavors, that.specialistFavors) && Objects.equals(certificate, that.certificate) && Objects.equals(sessions, that.sessions) && Objects.equals(specialistRating, that.specialistRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info, filename, finalRating, specialistFavors, certificate, sessions, specialistRating);
    }
}

package com.nikak.pspkurssecurity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Entity
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String filename;

    private double price;

    @ManyToMany(mappedBy = "specialistFavors")
    @JsonBackReference
    private List<Specialist> specialists = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favor favor = (Favor) o;
        return Double.compare(price, favor.price) == 0 && Objects.equals(id, favor.id) && Objects.equals(name, favor.name) && Objects.equals(filename, favor.filename) && Objects.equals(specialists, favor.specialists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, filename, price, specialists);
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<Specialist> getSpecialists() {
        return specialists;
    }



    public void setSpecialists(List<Specialist> specialists) {
        this.specialists = specialists;
    }



    @OneToMany(mappedBy="favor")
    @JsonBackReference
    private Set<Session> sessions;
}

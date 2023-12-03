package com.nikak.pspkurssecurity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name="specialist_id", nullable=false)
    @JsonBackReference
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    @JsonManagedReference
    private Client client;

    public Rating() {
    }

    public Rating(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Rating(Long id, Integer rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(id, rating1.id) && Objects.equals(rating, rating1.rating) && Objects.equals(comment, rating1.comment) && Objects.equals(specialist, rating1.specialist) && Objects.equals(client, rating1.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, comment, specialist, client);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}

package com.nikak.pspkurssecurity.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Column(name = "sessionDate")
    private LocalDate sessionDate;

    @ManyToOne
    @JoinColumn(name = "specialist_id", nullable = false)
    @JsonManagedReference
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "favor_id", nullable = false)
    @JsonManagedReference
    private Favor favor;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    @JsonManagedReference
    private Time time;



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    public Favor getFavor() {
        return favor;
    }

    public void setFavor(Favor favor) {
        this.favor = favor;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && payment == session.payment && Objects.equals(sessionDate, session.sessionDate) && Objects.equals(specialist, session.specialist) && Objects.equals(client, session.client) && Objects.equals(favor, session.favor) && Objects.equals(time, session.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payment, sessionDate, specialist, client, favor, time);
    }
}


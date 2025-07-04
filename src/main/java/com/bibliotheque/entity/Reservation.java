package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_reservation") // <-- adapter au nom réel
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    private LocalDate dateReservation;

    private String statut; // "active", "annulee", "traitee"

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Adherent getAdherent() { return adherent; }

    public void setAdherent(Adherent adherent) { this.adherent = adherent; }

    public Exemplaire getExemplaire() { return exemplaire; }

    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    public LocalDate getDateReservation() { return dateReservation; }

    public void setDateReservation(LocalDate dateReservation) { this.dateReservation = dateReservation; }

    public String getStatut() { return statut; }

    public void setStatut(String statut) { this.statut = statut; }
}

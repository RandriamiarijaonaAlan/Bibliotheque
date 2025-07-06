package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherent")
    private Adherent adherent;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    @Column(name = "date_debut_reservation")
    private LocalDate dateDebutReservation;

    @Column(name = "date_fin_reservation")
    private LocalDate dateFinReservation;

    @Column(length = 20)
    private String statut;

    // Getters et setters

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalDate getDateDebutReservation() {
        return dateDebutReservation;
    }

    public void setDateDebutReservation(LocalDate dateDebutReservation) {
        this.dateDebutReservation = dateDebutReservation;
    }

    public LocalDate getDateFinReservation() {
        return dateFinReservation;
    }

    public void setDateFinReservation(LocalDate dateFinReservation) {
        this.dateFinReservation = dateFinReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

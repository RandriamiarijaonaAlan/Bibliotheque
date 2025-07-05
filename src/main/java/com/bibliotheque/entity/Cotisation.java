package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cotisation")
public class Cotisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCotisation;

    @ManyToOne
    @JoinColumn(name = "id_adherent")
    private Adherent adherent;

    private String typeAdhesion;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private double montant;

    private boolean payee = false;

    @ManyToOne
    @JoinColumn(name = "valide_par")
    private Adherent validePar;

    // Getters et Setters
    public Long getIdCotisation() {
        return idCotisation;
    }

    public void setIdCotisation(Long idCotisation) {
        this.idCotisation = idCotisation;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public String getTypeAdhesion() {
        return typeAdhesion;
    }

    public void setTypeAdhesion(String typeAdhesion) {
        this.typeAdhesion = typeAdhesion;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public boolean isPayee() {
        return payee;
    }

    public void setPayee(boolean payee) {
        this.payee = payee;
    }

    public Adherent getValidePar() {
        return validePar;
    }

    public void setValidePar(Adherent validePar) {
        this.validePar = validePar;
    }
}

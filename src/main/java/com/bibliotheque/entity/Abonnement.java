package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abonnement")
    private Long idAbonnement;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "statut", nullable = false)
    private String statut = "en_attente";  // valeurs possibles : actif, expiré, en_attente

    // --- Getters & Setters ---

    public Long getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Long idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    // Setter et getter indirects pour gérer l'idAdherent sans charger l'entité complète
    public void setIdAdherent(Integer idAdherent) {
        if (idAdherent != null) {
            Adherent a = new Adherent();
            a.setIdAdherent(idAdherent);
            this.adherent = a;
        } else {
            this.adherent = null;
        }
    }

    public Integer getIdAdherent() {
        return (adherent != null) ? adherent.getIdAdherent() : null;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

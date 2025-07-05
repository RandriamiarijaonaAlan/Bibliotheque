package com.bibliotheque.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAbonnement;

    @ManyToOne
    @JoinColumn(name = "id_adherent")
    private Adherent adherent;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Column(nullable = false)
    private String statut = "en_attente"; // en_attente, actif, refuse, etc.

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

    // Getters / Setters
    
}

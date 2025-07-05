package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pret")
    private Long idPret;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @Column(name = "date_pret")
    private LocalDate datePret;

    @Column(name = "date_retour_prevue")
    private LocalDate dateRetourPrevue;

    @Column(name = "date_retour_reelle")
    private LocalDate dateRetourReelle;

    @Column(name = "type_lecture")
    private String typeLecture; // domicile, sur_place

    @Column(nullable = false)
    private Integer prolongements = 0;

    @Column(nullable = false)
    private String statut; // en_attente, valide, refuse

    // Constructeurs
    public Pret() {
        this.datePret = LocalDate.now();
        this.statut = "en_attente";
    }

    // Getters et setters
    public Long getIdPret() {
        return idPret;
    }

    public void setIdPret(Long idPret) {
        this.idPret = idPret;
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

    public LocalDate getDatePret() {
        return datePret;
    }

    public void setDatePret(LocalDate datePret) {
        this.datePret = datePret;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourReelle() {
        return dateRetourReelle;
    }

    public void setDateRetourReelle(LocalDate dateRetourReelle) {
        this.dateRetourReelle = dateRetourReelle;
    }

    public String getTypeLecture() {
        return typeLecture;
    }

    public void setTypeLecture(String typeLecture) {
        this.typeLecture = typeLecture;
    }

    public Integer getProlongements() {
        return prolongements;
    }

    public void setProlongements(Integer prolongements) {
        this.prolongements = prolongements;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

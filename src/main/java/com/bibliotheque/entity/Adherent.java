package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adherent")
public class Adherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adherent")
    private Integer idAdherent;

    @Column(name = "id_profil", nullable = false, unique = true)
    private Integer profilId;

    @Column(name = "id_type_adherent")
    private Integer typeAdherentId;

    @Column(name = "statut")
    private String statut;

    @Column(name = "quota_reservation")
    private Integer quotaReservation;

    @Column(name = "duree_pret_jours")
    private Integer dureePretJours;

    @Column(name = "prolongement_max")
    private Integer prolongementMax;

    @Column(name = "duree_prolongement_jours")
    private Integer dureeProlongementJours;

    @Column(name = "date_inscription")
    private java.time.LocalDate dateInscription;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_profil", insertable = false, updatable = false)
private Profil profil;

// + ajout des getter/setter
public Profil getProfil() {
    return profil;
}

public void setProfil(Profil profil) {
    this.profil = profil;
}

    // Getters et setters

    public Integer getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Integer idAdherent) {
        this.idAdherent = idAdherent;
    }

    public Integer getProfilId() {
        return profilId;
    }

    public void setProfilId(Integer profilId) {
        this.profilId = profilId;
    }

    public Integer getTypeAdherentId() {
        return typeAdherentId;
    }

    public void setTypeAdherentId(Integer typeAdherentId) {
        this.typeAdherentId = typeAdherentId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getQuotaReservation() {
        return quotaReservation;
    }

    public void setQuotaReservation(Integer quotaReservation) {
        this.quotaReservation = quotaReservation;
    }

    public Integer getDureePretJours() {
        return dureePretJours;
    }

    public void setDureePretJours(Integer dureePretJours) {
        this.dureePretJours = dureePretJours;
    }

    public Integer getProlongementMax() {
        return prolongementMax;
    }

    public void setProlongementMax(Integer prolongementMax) {
        this.prolongementMax = prolongementMax;
    }

    public Integer getDureeProlongementJours() {
        return dureeProlongementJours;
    }

    public void setDureeProlongementJours(Integer dureeProlongementJours) {
        this.dureeProlongementJours = dureeProlongementJours;
    }

    public java.time.LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(java.time.LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
    
}

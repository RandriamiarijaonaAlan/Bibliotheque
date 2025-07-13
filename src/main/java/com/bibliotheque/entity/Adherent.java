package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adherent")
public class Adherent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdherent;

    /** Relation 1‑N : un type pour plusieurs adhérents */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherent")
    private TypeAdherent typeAdherent;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_profil", nullable = false)
    private Profil profil;

    private Integer quotaReservation = 2;
    private Integer dureePretJours    = 14;
    private Integer prolongementMax   = 2;
    private Integer dureeProlongementJours = 7;

    private String statut = "inactif";  // actif | inactif | blackliste

    public Long getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    public boolean isValide() {
    return "actif".equalsIgnoreCase(this.statut);
}

    public boolean isAbonne() {
    // Par exemple, considère qu’un adhérent est abonné s’il a un type d’adhérent valide
    return this.typeAdherent != null && this.typeAdherent.isAbonne(); // Ou adapte selon ta logique
}

    /* getters & setters */
    
}

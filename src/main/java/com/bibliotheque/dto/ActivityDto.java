package com.bibliotheque.dto;

import java.time.LocalDate;

public class ActivityDto {
    private Long id;
    private String type;
    private String nomAdherent;
    private String titreLivre;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    // Constructeur vide (obligatoire)
    public ActivityDto() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getNomAdherent() {
        return nomAdherent;
    }
    public void setNomAdherent(String nomAdherent) {
        this.nomAdherent = nomAdherent;
    }

    public String getTitreLivre() {
        return titreLivre;
    }
    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
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
}

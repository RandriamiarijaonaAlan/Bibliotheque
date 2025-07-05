package com.bibliotheque.dto;

import java.time.LocalDate;

public class ActivityDto {
    private Long id;
    private String nomAdherent;
    private String titreLivre;
    private LocalDate date;
    private String type;  // Reservation, Pret, Prolongement, Abonnement
    private String statut;

    public ActivityDto(Long id, String nomAdherent, String titreLivre, LocalDate date, String type, String statut) {
        this.id = id;
        this.nomAdherent = nomAdherent;
        this.titreLivre = titreLivre;
        this.date = date;
        this.type = type;
        this.statut = statut;
    }

    // Getters et setters
    public Long getId() { return id; }
    public String getNomAdherent() { return nomAdherent; }
    public String getTitreLivre() { return titreLivre; }
    public LocalDate getDate() { return date; }
    public String getType() { return type; }
    public String getStatut() { return statut; }

    public void setId(Long id) { this.id = id; }
    public void setNomAdherent(String nomAdherent) { this.nomAdherent = nomAdherent; }
    public void setTitreLivre(String titreLivre) { this.titreLivre = titreLivre; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setType(String type) { this.type = type; }
    public void setStatut(String statut) { this.statut = statut; }
}

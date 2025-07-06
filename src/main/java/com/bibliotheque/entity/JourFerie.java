package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jour_ferie")
public class JourFerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jour")
    private Long idJour;

    @Column(name = "date", unique = true, nullable = false)
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Column(name = "politique_retour")
    private String politiqueRetour;

    // Constructeurs

    public JourFerie() {}

    public JourFerie(LocalDate date, String description, String politiqueRetour) {
        this.date = date;
        this.description = description;
        this.politiqueRetour = politiqueRetour;
    }

    // Getters & Setters

    public Long getIdJour() {
        return idJour;
    }

    public void setIdJour(Long idJour) {
        this.idJour = idJour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPolitiqueRetour() {
        return politiqueRetour;
    }

    public void setPolitiqueRetour(String politiqueRetour) {
        this.politiqueRetour = politiqueRetour;
    }
}

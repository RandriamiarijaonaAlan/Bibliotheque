package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "activite")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adherentNom; // Pour simplifier, on stocke juste le nom ici

    private String livreTitre;  // Idem pour le titre du livre

    private LocalDate date;

    private String type; // "reservation", "prolongement", "prêt", "abonnement"

    private boolean valide = false;

    // Getters et setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAdherentNom() { return adherentNom; }
    public void setAdherentNom(String adherentNom) { this.adherentNom = adherentNom; }

    public String getLivreTitre() { return livreTitre; }
    public void setLivreTitre(String livreTitre) { this.livreTitre = livreTitre; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isValide() { return valide; }
    public void setValide(boolean valide) { this.valide = valide; }
}

package com.bibliotheque.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class InscriptionDTO {

    @NotBlank private String nom;
    @NotBlank private String prenom;

    @Email @NotBlank
    private String email;

    @Size(min = 6, message = "Mot de passe trop court")
    private String motDePasse;

    @NotNull
    private Long idTypeAdherent;

    @Past
    private LocalDate dateDeNaissance;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Long getIdTypeAdherent() {
        return idTypeAdherent;
    }

    public void setIdTypeAdherent(Long idTypeAdherent) {
        this.idTypeAdherent = idTypeAdherent;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /* getters & setters */
    
}

package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profil")
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfil;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;  // si tu as ce champ

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "mot_de_passe", nullable = false, length = 255)
    private String motDePasse;

    @Column(name = "date_de_naissance", nullable = false)
    private LocalDate dateDeNaissance;

    @Column(name = "code_admin", length = 4)
    private String codeAdmin;

    // Getters et setters...

    public Integer getIdProfil() { return idProfil; }
    public void setIdProfil(Integer idProfil) { this.idProfil = idProfil; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public LocalDate getDateDeNaissance() { return dateDeNaissance; }
    public void setDateDeNaissance(LocalDate dateDeNaissance) { this.dateDeNaissance = dateDeNaissance; }

    public String getCodeAdmin() { return codeAdmin; }
    public void setCodeAdmin(String codeAdmin) { this.codeAdmin = codeAdmin; }
}


    /* getters & setters */
    


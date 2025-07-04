package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExemplaire;


    @ManyToOne
    @JoinColumn(name = "id_livre")
    private Livre livre;

    private String codeBarre;

    private String etat;

    private String localisation;

    private Boolean autoriseLectureSurPlace = true;

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Boolean getAutoriseLectureSurPlace() {
        return autoriseLectureSurPlace;
    }

    public void setAutoriseLectureSurPlace(Boolean autoriseLectureSurPlace) {
        this.autoriseLectureSurPlace = autoriseLectureSurPlace;
    }

    // Getters et Setters
    
}

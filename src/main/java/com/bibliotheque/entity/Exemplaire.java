package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplaire")
    private Long idExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Column(name = "titre_exemplaire", length = 255)
    private String titreExemplaire;

    @Column(name = "code_barre", unique = true, length = 50)
    private String codeBarre;

    @Column(length = 20)
    private String etat;  // disponible, en_pret, en_reservation, lecture_sur_place

    @Column(length = 100)
    private String localisation;

    @Column(name = "autorise_lecture_sur_place")
    private Boolean autoriseLectureSurPlace = true;

    // Constructeurs
    public Exemplaire() {}

    // Getters et setters

    public Long getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Long idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public String getTitreExemplaire() {
        return titreExemplaire;
    }

    public void setTitreExemplaire(String titreExemplaire) {
        this.titreExemplaire = titreExemplaire;
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
}

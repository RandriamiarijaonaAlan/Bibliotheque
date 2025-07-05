package com.bibliotheque.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prolongement")
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProlongement;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @Column(name = "date_prolongement")
    private LocalDate dateProlongement = LocalDate.now();

    @Column(name = "nouvelle_date_retour")
    private LocalDate nouvelleDateRetour;

    private String statut = "en_attente"; // valeurs : en_attente, valide, refuse

    // Getters et Setters
    public Long getIdProlongement() {
        return idProlongement;
    }

    public void setIdProlongement(Long idProlongement) {
        this.idProlongement = idProlongement;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public LocalDate getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDate dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public LocalDate getNouvelleDateRetour() {
        return nouvelleDateRetour;
    }

    public void setNouvelleDateRetour(LocalDate nouvelleDateRetour) {
        this.nouvelleDateRetour = nouvelleDateRetour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

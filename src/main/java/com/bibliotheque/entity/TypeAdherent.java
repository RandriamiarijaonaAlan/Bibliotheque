package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_adherent")
    private Integer idTypeAdherent;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    public Integer getIdTypeAdherent() {
        return idTypeAdherent;
    }

    public void setIdTypeAdherent(Integer idTypeAdherent) {
        this.idTypeAdherent = idTypeAdherent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

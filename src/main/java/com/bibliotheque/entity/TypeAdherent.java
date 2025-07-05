package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeAdherent;

    @Column(length = 100, nullable = false, unique = true)
    private String nom;

    /** Code à 4 chiffres pour les bibliothécaires (nullable sinon) */
    @Column(name = "code")
private Integer code;


    public Long getIdTypeAdherent() {
        return idTypeAdherent;
    }

    public void setIdTypeAdherent(Long idTypeAdherent) {
        this.idTypeAdherent = idTypeAdherent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /* getters & setters */
    
}

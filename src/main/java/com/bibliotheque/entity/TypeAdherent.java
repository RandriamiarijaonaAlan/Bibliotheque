package com.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_adherent")
    private Integer id;

    @Column(name = "nom", unique = true, nullable = false)
    private String nom;

    // Supposons que "code" est un Integer (car en base c’est INT)
    @Column(name = "code")
    private Integer code;

    // Getter et setter pour id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter et setter pour nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et setter pour code - **ici il faut que les types correspondent à Integer**
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
     public boolean isAbonne() {
        return "Abonne".equalsIgnoreCase(this.nom);
    }
}

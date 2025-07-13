package com.bibliotheque.service;

import com.bibliotheque.dto.InscriptionDTO;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Profil;
import com.bibliotheque.entity.TypeAdherent;
import com.bibliotheque.repository.AdherentRepository;
import com.bibliotheque.repository.ProfilRepository;
import com.bibliotheque.repository.TypeAdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class InscriptionService {

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    private final SecureRandom random = new SecureRandom();

    /**
     * Inscription d'un utilisateur.
     * @param dto Données du formulaire d'inscription
     * @return Le code admin 4 chiffres si bibliothécaire, sinon null
     */
    public String inscrire(InscriptionDTO dto) {
        // 1. Récupérer le TypeAdherent par ID
        Optional<TypeAdherent> optionalType = typeAdherentRepository.findById(dto.getIdTypeAdherent());
        if (optionalType.isEmpty()) {
            throw new IllegalArgumentException("Type d'adhérent invalide");
        }
        TypeAdherent typeAdherent = optionalType.get();

        // 2. Créer et remplir le Profil
        Profil profil = new Profil();
        profil.setNom(dto.getNom());
        profil.setPrenom(dto.getPrenom()); // à créer dans Profil si nécessaire
        profil.setEmail(dto.getEmail());
        profil.setMotDePasse(dto.getMotDePasse());
        profil.setDateDeNaissance(dto.getDateDeNaissance());

        // Générer un code admin si c'est un bibliothécaire
        if ("bibliothecaire".equalsIgnoreCase(typeAdherent.getNom())) {
            String codeAdmin = generateCodeAdmin();
            profil.setCodeAdmin(codeAdmin);
        }

        profilRepository.save(profil);

        // 3. Créer et remplir l'Adherent
        Adherent adherent = new Adherent();
        adherent.setProfil(profil);
        adherent.setTypeAdherent(typeAdherent);
        // Les autres champs (quota, durée prêt, etc.) peuvent rester aux valeurs par défaut
        adherentRepository.save(adherent);

        // 4. Retourner le code admin si bibliothécaire, sinon null
        if ("bibliothecaire".equalsIgnoreCase(typeAdherent.getNom())) {
            return profil.getCodeAdmin();
        } else {
            return null;
        }
    }

    /**
     * Génère un code admin aléatoire à 4 chiffres.
     * @return code à 4 chiffres sous forme de String (ex: "0427")
     */
    private String generateCodeAdmin() {
        int code = random.nextInt(10000); // 0 à 9999
        return String.format("%04d", code);
    }
}

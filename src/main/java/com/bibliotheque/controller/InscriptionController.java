package com.bibliotheque.controller;

import com.bibliotheque.entity.*;
import com.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class InscriptionController {

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private TypeAdherentRepository typeAdherentRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @GetMapping("/inscription")
    public String showInscriptionForm(Model model) {
        model.addAttribute("profil", new Profil());
        model.addAttribute("prenom", "");
        model.addAttribute("typesAdherents", typeAdherentRepository.findAll());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String inscrireProfil(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("motDePasse") String motDePasse,
            @RequestParam("idTypeAdherent") Integer idTypeAdherent,
            Model model
    ) {
        if (nom == null || nom.trim().isEmpty() ||
            prenom == null || prenom.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            motDePasse == null || motDePasse.trim().isEmpty() ||
            idTypeAdherent == null) {

            Profil profil = new Profil();
            profil.setNom(nom != null ? nom : "");
            profil.setEmail(email != null ? email : "");
            profil.setMotDePasse(motDePasse != null ? motDePasse : "");

            model.addAttribute("profil", profil);
            model.addAttribute("prenom", prenom != null ? prenom : "");
            model.addAttribute("typesAdherents", typeAdherentRepository.findAll());
            model.addAttribute("error", "Tous les champs sont obligatoires.");
            return "inscription";
        }

        // 1. Créer le profil
        Profil profil = new Profil();
        profil.setNom(nom + " " + prenom);
        profil.setEmail(email);
        profil.setMotDePasse(motDePasse);
        profilRepository.save(profil);

        // 2. Créer l'adhérent (liens manuels avec ID Integer)
        Adherent adherent = new Adherent();
        adherent.setProfilId(profil.getIdProfil().intValue());  // conversion Long -> Integer
        adherent.setTypeAdherentId(idTypeAdherent);
        adherent.setStatut("actif"); // facultatif
        adherentRepository.save(adherent);

        // 3. Créer l’abonnement de 30 jours
        Abonnement abonnement = new Abonnement();
        abonnement.setIdAdherent(adherent.getIdAdherent());
        abonnement.setDateDebut(LocalDate.now());
        abonnement.setDateFin(LocalDate.now().plusDays(30));
        abonnement.setStatut("actif");
        abonnementRepository.save(abonnement);

        return "redirect:/login";
    }
}

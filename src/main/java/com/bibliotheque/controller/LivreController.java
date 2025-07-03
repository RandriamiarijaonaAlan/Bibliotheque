package com.bibliotheque.controller;

import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.repository.ExemplaireRepository;
import com.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @GetMapping("/livres/ajouter")
    public String showForm(Model model) {
        model.addAttribute("livre", new Livre());
        model.addAttribute("exemplaire", new Exemplaire());
        return "ajout-livre";
    }

    @PostMapping("/livres/ajouter")
    public String ajouterLivreEtExemplaire(
            @ModelAttribute Livre livre,
            @RequestParam String codeBarre,
            @RequestParam String localisation,
            @RequestParam(defaultValue = "disponible") String etat
    ) {
        Livre livreEnregistre = livreRepository.save(livre);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setLivre(livreEnregistre);
        exemplaire.setCodeBarre(codeBarre);
        exemplaire.setLocalisation(localisation);
        exemplaire.setEtat(etat);
        exemplaire.setAutoriseLectureSurPlace(true);

        exemplaireRepository.save(exemplaire);

        return "redirect:/livres/ajouter?success";
    }
}

package com.bibliotheque.controller;

import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.repository.ExemplaireRepository;
import com.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.*;

@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    // Lister tous les livres
    @GetMapping
    public String listerLivres(Model model) {
        model.addAttribute("livres", livreRepository.findAll());
        return "livre_list";  // Vue liste des livres
    }

    // Afficher formulaire ajout ou modification
    @GetMapping({"/ajouter", "/modifier/{id}"})
    public String showForm(@PathVariable(required = false) Integer id, Model model) {
        Livre livre;
        if (id != null) {
            Optional<Livre> optLivre = livreRepository.findById(id);
            if (optLivre.isPresent()) {
                livre = optLivre.get();
            } else {
                return "redirect:/livres?error=notfound";
            }
        } else {
            livre = new Livre();
        }
        model.addAttribute("livre", livre);
        return "ajout-livre"; // Formulaire Thymeleaf pour ajout/modification
    }

    // Ajouter ou modifier un livre
    @PostMapping("/enregistrer")
    public String enregistrerLivre(@ModelAttribute Livre livre) {
        livreRepository.save(livre);
        return "redirect:/livres?success";
    }

 @GetMapping("/supprimer/{id}")
public String supprimerLivre(@PathVariable Integer id) {
    Livre livre = livreRepository.findById(id).orElseThrow();

    // Supprimer les exemplaires liés d'abord
    List<Exemplaire> exemplaires = exemplaireRepository.findByLivre(livre);
    exemplaireRepository.deleteAll(exemplaires);

    // Ensuite, supprimer le livre
    livreRepository.delete(livre);

    return "redirect:/livres";
}
    // Ajouter un exemplaire à un livre (séparé de la gestion livre)
    @PostMapping("/{livreId}/exemplaires/ajouter")
    public String ajouterExemplaire(
            @PathVariable Integer livreId,
            @RequestParam String codeBarre,
            @RequestParam String localisation,
            @RequestParam(defaultValue = "disponible") String etat,
            Model model
    ) {
        Optional<Livre> optLivre = livreRepository.findById(livreId);
        if (optLivre.isEmpty()) {
            return "redirect:/livres?error=livrenotfound";
        }
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setLivre(optLivre.get());
        exemplaire.setCodeBarre(codeBarre);
        exemplaire.setLocalisation(localisation);
        exemplaire.setEtat(etat);
        exemplaire.setAutoriseLectureSurPlace(true);

        exemplaireRepository.save(exemplaire);
        return "redirect:/livres/" + livreId + "?successExemplaire";
    }
}

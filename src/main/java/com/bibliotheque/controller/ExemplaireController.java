package com.bibliotheque.controller;
import com.bibliotheque.entity.*;
import com.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/exemplaire")
public class ExemplaireController {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/creation")
    public String formExemplaire(Model model) {
        model.addAttribute("exemplaire", new Exemplaire());
        model.addAttribute("livres", livreRepository.findAll());
        return "exemplaire_form";
    }

  @PostMapping("/creation")
public String saveExemplaire(@ModelAttribute Exemplaire exemplaire,
                             @RequestParam("livreId") Long livreId,
                             Model model) {
    Livre livre = livreRepository.findById(livreId).orElse(null);
    if (livre == null) {
        model.addAttribute("error", "Livre non trouvé");
        model.addAttribute("livres", livreRepository.findAll()); // Pour recharger la liste
        return "exemplaire_form";
    }
    exemplaire.setLivre(livre);
    exemplaireRepository.save(exemplaire);
    model.addAttribute("message", "Exemplaire enregistré avec succès !");
    model.addAttribute("livres", livreRepository.findAll()); // Pour recharger la liste
    model.addAttribute("exemplaire", new Exemplaire()); // Reset du formulaire
    return "exemplaire_form";
}

}


package com.bibliotheque.controller;

import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.repository.AbonnementRepository;
import com.bibliotheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/abonnement")
public class AdminAbonnementController {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    // Affiche la liste des abonnements en attente
    @GetMapping("/validation")
    public String listeDemandes(Model model) {
        List<Abonnement> demandes = abonnementRepository.findByStatut("en_attente");
        model.addAttribute("demandes", demandes);
        return "admin_abonnement_validation";
    }

    // Valider un abonnement
    @PostMapping("/valider")
    public String validerAbonnement(@RequestParam Long id, RedirectAttributes ra) {
        Optional<Abonnement> opt = abonnementRepository.findById(id);
        if (opt.isPresent()) {
            Abonnement abo = opt.get();
            abo.setStatut("actif");
            abonnementRepository.save(abo);
            ra.addFlashAttribute("message", "Abonnement validé avec succès.");
        } else {
            ra.addFlashAttribute("erreur", "Abonnement introuvable.");
        }
        return "redirect:/admin/abonnement/validation";
    }

    // Refuser un abonnement
    @PostMapping("/refuser")
    public String refuserAbonnement(@RequestParam Long id, RedirectAttributes ra) {
        Optional<Abonnement> opt = abonnementRepository.findById(id);
        if (opt.isPresent()) {
            Abonnement abo = opt.get();
            abo.setStatut("refuse");
            abonnementRepository.save(abo);
            ra.addFlashAttribute("message", "Abonnement refusé.");
        } else {
            ra.addFlashAttribute("erreur", "Abonnement introuvable.");
        }
        return "redirect:/admin/abonnement/validation";
    }
}

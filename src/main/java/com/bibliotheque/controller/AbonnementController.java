package com.bibliotheque.controller;

import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.repository.AbonnementRepository;
import com.bibliotheque.repository.AdherentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/abonnement")
public class AbonnementController {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @GetMapping("/form")
    public String showForm(Model model) {
        List<Adherent> adherents = adherentRepository.findAll();
        model.addAttribute("adherents", adherents);
        return "abonnement_form";
    }

    @PostMapping("/demande")
    public String demanderAbonnement(@RequestParam("idAdherent") Long idAdherent,
                                     @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
                                     @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
                                     RedirectAttributes redirectAttributes) {

        Optional<Adherent> adherentOpt = adherentRepository.findById(idAdherent);
        if (adherentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("erreur", "Adhérent introuvable.");
            return "redirect:/abonnement/form";
        }

        Abonnement abonnement = new Abonnement();
        abonnement.setAdherent(adherentOpt.get());
        abonnement.setDateDebut(dateDebut);
        abonnement.setDateFin(dateFin);
        abonnement.setStatut("en_attente");

        abonnementRepository.save(abonnement);

        redirectAttributes.addFlashAttribute("message", "Demande envoyée avec succès !");
        return "redirect:/abonnement/form";
    }
}

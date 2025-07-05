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
import java.util.Optional;

@Controller
@RequestMapping("/abonnement")
public class AbonnementController {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    /* ---------- Formulaire GET ---------- */
    @GetMapping("/form")
    public String showForm(Model model) {
        // Les messages « flash » (succès / erreur) sont déjà injectés par Spring
        return "abonnement_form";
    }

    /* ---------- Soumission POST ---------- */
    @PostMapping("/demande")
    public String demanderAbonnement(
            @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam("dateFin")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            HttpSession session,
            RedirectAttributes ra) {

        /* 1. Récupération de l’adhérent connecté (en session) */
        Long idAdherent = (Long) session.getAttribute("idAdherent");
        if (idAdherent == null) {
            ra.addFlashAttribute("erreur", "Vous devez être connecté pour faire une demande d’abonnement.");
            return "redirect:/login";
        }

        /* 2. Vérification que l’adhérent existe */
        Optional<Adherent> optAdh = adherentRepository.findById(idAdherent);
        if (optAdh.isEmpty()) {
            ra.addFlashAttribute("erreur", "Adhérent introuvable.");
            return "redirect:/";
        }

        /* 3. Enregistrement de la demande (statut = en_attente) */
        Abonnement abo = new Abonnement();
        abo.setAdherent(optAdh.get());
        abo.setDateDebut(dateDebut);
        abo.setDateFin(dateFin);
        abo.setStatut("en_attente");

        abonnementRepository.save(abo);   // persiste la demande

        /* 4. Message de succès et redirection */
        ra.addFlashAttribute("message", "Votre demande d’abonnement a été envoyée au bibliothécaire pour validation.");
        return "redirect:/abonnement/form";
    }
}

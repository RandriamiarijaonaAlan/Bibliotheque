package com.bibliotheque.controller;

import com.bibliotheque.entity.*;
import com.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/prets")
public class PretController {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PretRepository pretRepository;

    /* ---------- FORMULAIRE ---------- */

    @GetMapping("/new")              // URL finale : /prets/new
    public String afficherFormulairePret(Model model) {
        model.addAttribute("adherents", adherentRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "pret_form";          // src/main/resources/templates/pret_form.html
    }

    /* ---------- TRAITEMENT ---------- */

    @PostMapping                     // le <form th:action="@{/prets}" …>
    public String creerPret(
            @RequestParam Integer idAdherent,
            @RequestParam Integer idExemplaire,
            @RequestParam String typeLecture,
            Model model
    ) {
        Optional<Adherent> optAdherent = adherentRepository.findById(idAdherent);
        Optional<Exemplaire> optExemplaire = exemplaireRepository.findById(idExemplaire);

        if (optAdherent.isEmpty() || optExemplaire.isEmpty()) {
            model.addAttribute("error", "Adhérent ou exemplaire invalide");
            model.addAttribute("adherents", adherentRepository.findAll());
            model.addAttribute("exemplaires", exemplaireRepository.findAll());
            return "pret_form";
        }

        Pret pret = new Pret();
        pret.setAdherent(optAdherent.get());
        pret.setExemplaire(optExemplaire.get());
        pret.setDatePret(LocalDate.now());
        pret.setDateRetourPrevue(
                LocalDate.now().plusDays(
                    Optional.ofNullable(optAdherent.get().getDureePretJours()).orElse(14)
                )
        );
        pret.setTypeLecture(typeLecture);
        pret.setProlongements(0);

        pretRepository.save(pret);

        return "redirect:/prets/new?success";   // retour au formulaire avec succès
    }
}

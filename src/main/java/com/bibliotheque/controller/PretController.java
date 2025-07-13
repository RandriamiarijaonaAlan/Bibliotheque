package com.bibliotheque.controller;

import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Pret;
import com.bibliotheque.repository.AdherentRepository;
import com.bibliotheque.repository.ExemplaireRepository;
import com.bibliotheque.service.PretService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @GetMapping("/formulaire")
    public String afficherFormulairePret(Model model) {
        List<Adherent> adherents = adherentRepository.findAll();
        List<Exemplaire> exemplaires = exemplaireRepository.findAll();

        model.addAttribute("adherents", adherents);
        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("pret", new Pret());

        return "pret_formulaire";
    }

  @PostMapping("/enregistrer")
public String enregistrerPret(
    @RequestParam Long idAdherent,
    @RequestParam Long idExemplaire,
    @RequestParam("dateRetourPrevue") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateRetourPrevue,
    @RequestParam String typeLecture,
    Model model) {

    try {
        pretService.creerPret(idAdherent, idExemplaire, dateRetourPrevue, typeLecture);
        model.addAttribute("success", "Prêt créé avec succès !");
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
    }

    model.addAttribute("adherents", adherentRepository.findAll());
    model.addAttribute("exemplaires", exemplaireRepository.findAll());
    model.addAttribute("pret", new Pret());

    return "pret_formulaire";
}



}

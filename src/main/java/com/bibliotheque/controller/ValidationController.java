package com.bibliotheque.controller;

import com.bibliotheque.dto.ActivityDto;
import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.service.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bibliotheque.repository.AbonnementRepository;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @GetMapping("/validation")
    public String showValidationPage(Model model, @RequestParam(value = "search", required = false) String search) {
        List<ActivityDto> activities = validationService.getAllActivities();
         List<Abonnement> abonnementsEnAttente = abonnementRepository.findByStatut("en_attente");
        model.addAttribute("abonnementsEnAttente", abonnementsEnAttente);
        // Filtrer si recherche (par titre livre ou nom adherent)
        if (search != null && !search.isEmpty()) {
            String lowerSearch = search.toLowerCase();
            activities = activities.stream()
                .filter(a -> a.getTitreLivre().toLowerCase().contains(lowerSearch)
                          || a.getNomAdherent().toLowerCase().contains(lowerSearch))
                .toList();
        }

        model.addAttribute("activities", activities);
        model.addAttribute("search", search);
        return "validation_page";
    }

    // Validation actions via POST, ici exemples

    @PostMapping("/validation/reservation/{id}")
    public String validerReservation(@PathVariable Long id) {
        validationService.validerReservation(id);
        return "redirect:/admin/validation";
    }

    @PostMapping("/validation/pret/{id}")
    public String validerPret(@PathVariable Long id) {
        validationService.validerPret(id);
        return "redirect:/admin/validation";
    }

    @PostMapping("/validation/prolongement/{id}")
    public String validerProlongement(@PathVariable Long id) {
        validationService.validerProlongement(id);
        return "redirect:/admin/validation";
    }

    @PostMapping("/validation/abonnement/{id}")
    public String validerAbonnement(@PathVariable Long id) {
        validationService.validerAbonnement(id);
        return "redirect:/admin/validation";
    }
}

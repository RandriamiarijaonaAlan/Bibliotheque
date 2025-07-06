package com.bibliotheque.controller;

import com.bibliotheque.dto.ActivityDto;
import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.entity.Pret;
import com.bibliotheque.entity.Profil;
import com.bibliotheque.service.ValidationService;
import com.bibliotheque.repository.PretRepository;
import com.bibliotheque.service.PretService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bibliotheque.repository.AbonnementRepository;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ValidationController {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private PretRepository pretRepository;


  // Supposons que le bibliothécaire ait un code de 4 chiffres
@GetMapping("/validation")
public String showValidationPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    List<ActivityDto> activities = validationService.getAllActivities();
    model.addAttribute("activities", activities);
    return "validation_page";
}

@GetMapping("/abonnement/form")  // ou mapping selon ton besoin
public String afficherValidationPret(Model model) {
    List<Pret> pretsEnAttente = pretRepository.findByStatut("en_attente");
    model.addAttribute("pretsEnAttente", pretsEnAttente);
    return "validation_pret"; // nom de ta vue Thymeleaf
}




    // Validation actions via POST, ici exemples

    @PostMapping("/validation/reservation/{id}")
    public String validerReservation(@PathVariable Long id) {
        validationService.validerReservation(id);
        return "redirect:/admin/validation";
    }

  @PostMapping("/validation/pret/valider/{id}")
public String validerPret(@PathVariable Long id) {
    validationService.validerPret(id);
    return "redirect:/admin/abonnement/form";
}

@PostMapping("/validation/pret/refuser/{id}")
public String refuserPret(@PathVariable Long id) {
    validationService.refuserPret(id);
    return "redirect:/admin/abonnement/form";
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

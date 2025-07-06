package com.bibliotheque.controller;

import com.bibliotheque.dto.ActivityDto;
import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.entity.Profil;
import com.bibliotheque.service.ValidationService;

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

  // Supposons que le bibliothécaire ait un code de 4 chiffres
@GetMapping("/validation")
public String showValidationPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    List<ActivityDto> activities = validationService.getAllActivities();
    model.addAttribute("activities", activities);
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

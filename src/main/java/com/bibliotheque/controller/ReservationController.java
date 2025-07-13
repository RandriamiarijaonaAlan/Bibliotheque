package com.bibliotheque.controller;

import com.bibliotheque.service.ReservationService;
import com.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired private ReservationService reservationService;
    @Autowired private ExemplaireRepository exemplaireRepository;
    @Autowired private AdherentRepository adherentRepository;

    @GetMapping("/form")
public String formReservation(Model model) {
    model.addAttribute("exemplaires", exemplaireRepository.findAll());
    model.addAttribute("adherents", adherentRepository.findAll());
    return "reservation_form";
}


    @PostMapping("/demander")
    public String demanderReservation(
            @RequestParam Long idAdherent,
            @RequestParam Long idExemplaire,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebutReservation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinReservation,
            Model model) {

        String message = reservationService.demanderReservation(idAdherent, idExemplaire, dateDebutReservation, dateFinReservation);
        model.addAttribute("message", message);
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "reservation_form";
    }
}

package com.bibliotheque.controller;

import com.bibliotheque.entity.Reservation;
import com.bibliotheque.repository.ReservationRepository;
import com.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reservation")
public class AdminReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepo;

    @GetMapping
    public String voirDemandes(Model model) {
        model.addAttribute("reservations", reservationRepo.findByStatut("en_attente"));
        return "admin_reservations"; // Template Thymeleaf à créer
    }

    @PostMapping("/valider/{id}")
    public String valider(@PathVariable Long id) {
    reservationService.validerReservation(id);
    return "redirect:/admin/reservation"; // Ou la page que tu souhaites afficher après validation
}

    @PostMapping("/refuser/{id}")
    public String refuser(@PathVariable Long id) {
        reservationService.refuserReservation(id);
        return "redirect:/admin/reservation";
    }
    @GetMapping("/par-adherent")
public String reservationsParAdherent(@RequestParam(value = "idAdherent", required = false) Long idAdherent, Model model) {
    model.addAttribute("adherents", reservationService.getAllAdherents());

    if (idAdherent != null) {
        model.addAttribute("reservations", reservationService.getReservationsParAdherent(idAdherent));
        model.addAttribute("selectedId", idAdherent);
    }

    return "reservations_par_adherent";
}

}

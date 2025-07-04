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
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    // Afficher formulaire de réservation
    @GetMapping("/new")
    public String afficherFormulaire(Model model) {
        model.addAttribute("adherents", adherentRepository.findAll());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        return "reservation_form";
    }

    // Créer une réservation
    @PostMapping
    public String reserverExemplaire(
            @RequestParam Integer idAdherent,
            @RequestParam Integer idExemplaire,
            Model model) {

        Optional<Adherent> optAdherent = adherentRepository.findById(idAdherent);
        Optional<Exemplaire> optExemplaire = exemplaireRepository.findById(idExemplaire);

        if (optAdherent.isEmpty() || optExemplaire.isEmpty()) {
            model.addAttribute("error", "Adhérent ou exemplaire invalide");
            return "reservation_form";
        }

        Adherent adherent = optAdherent.get();
        Exemplaire exemplaire = optExemplaire.get();

        if ("disponible".equalsIgnoreCase(exemplaire.getEtat())) {
            model.addAttribute("error", "Exemplaire déjà disponible, inutile de le réserver.");
            return "reservation_form";
        }

        // Vérifier si réservation déjà active
        boolean existe = reservationRepository
            .findByAdherentAndExemplaireAndStatut(adherent, exemplaire, "active")
            .isPresent();

        if (existe) {
            model.addAttribute("error", "Réservation déjà existante pour cet exemplaire.");
            return "reservation_form";
        }

        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setExemplaire(exemplaire);
        reservation.setDateReservation(LocalDate.now());
        reservation.setStatut("active");

        reservationRepository.save(reservation);
        return "redirect:/reservations?success";
    }

    // Liste des réservations
    @GetMapping
    public String listeReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findByStatut("active");
        model.addAttribute("reservations", reservations);
        return "reservations_list";
    }

    // Annuler une réservation
    @PostMapping("/annuler/{id}")
    public String annulerReservation(@PathVariable Long id) {
        Optional<Reservation> opt = reservationRepository.findById(id);
        if (opt.isPresent()) {
            Reservation res = opt.get();
            res.setStatut("annulee");
            reservationRepository.save(res);
        }
        return "redirect:/reservations";
    }
}

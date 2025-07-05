package com.bibliotheque.service;

import com.bibliotheque.dto.ActivityDto;
import com.bibliotheque.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private CotisationRepository cotisationRepository;

    public List<ActivityDto> getAllActivities() {
        List<ActivityDto> activities = new ArrayList<>();

        // Reservations
        reservationRepository.findAll().forEach(res -> {
            activities.add(new ActivityDto(
                res.getIdReservation(),
                res.getAdherent().getProfil().getNom(),
                res.getExemplaire().getLivre().getTitre(),
                res.getDateReservation(),
                "Reservation",
                res.getStatut()
            ));
        });

        // Prêts
        pretRepository.findAll().forEach(pret -> {
            activities.add(new ActivityDto(
                pret.getIdPret(),
                pret.getAdherent().getProfil().getNom(),
                pret.getExemplaire().getLivre().getTitre(),
                pret.getDatePret(),
                "Prêt",
                pret.getStatut()
            ));
        });

        // Prolongements
        prolongementRepository.findAll().forEach(prol -> {
            activities.add(new ActivityDto(
                prol.getIdProlongement(),
                prol.getPret().getAdherent().getProfil().getNom(),
                prol.getPret().getExemplaire().getLivre().getTitre(),
                prol.getDateProlongement(),
                "Prolongement",
                prol.getStatut()
            ));
        });

        // Abonnements / Cotisations
        cotisationRepository.findAll().forEach(cot -> {
            activities.add(new ActivityDto(
                cot.getIdCotisation(),
                cot.getAdherent().getProfil().getNom(),
                "-", // Pas de livre
                cot.getDateDebut(),
                "Abonnement",
                cot.isPayee() ? "Payée" : "Non payée"
            ));
        });

        // Trier par date décroissante (récent d'abord)
        activities.sort(Comparator.comparing(ActivityDto::getDate).reversed());

        return activities;
    }

    // Méthodes pour valider les actions (exemples)

    public void validerReservation(Long idReservation) {
        var reservation = reservationRepository.findById(idReservation).orElseThrow();
        reservation.setStatut("reservee");
        reservationRepository.save(reservation);
    }

    public void validerPret(Long idPret) {
        var pret = pretRepository.findById(idPret).orElseThrow();
        pret.setStatut("valide");
        pretRepository.save(pret);
    }

    public void validerProlongement(Long idProlongement) {
        var prolongement = prolongementRepository.findById(idProlongement).orElseThrow();
        prolongement.setStatut("valide");
        prolongementRepository.save(prolongement);
    }

    public void validerAbonnement(Long idCotisation) {
        var cotisation = cotisationRepository.findById(idCotisation).orElseThrow();
        cotisation.setPayee(true);
        cotisationRepository.save(cotisation);
    }
}

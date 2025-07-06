package com.bibliotheque.service;

import com.bibliotheque.entity.Reservation;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.repository.ReservationRepository;
import com.bibliotheque.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ValidationReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Transactional
    public void validerReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

        // Vérifie si l'exemplaire est disponible
        Exemplaire exemplaire = reservation.getExemplaire();
        if (!"disponible".equalsIgnoreCase(exemplaire.getEtat())) {
            throw new RuntimeException("L'exemplaire n'est pas disponible pour réservation.");
        }

        // Mise à jour de l'état de l'exemplaire et de la réservation
        reservation.setStatut("reservee");
        exemplaire.setEtat("reserve");

        // Sauvegarde des modifications
        reservationRepository.save(reservation);
        exemplaireRepository.save(exemplaire);
    }
}

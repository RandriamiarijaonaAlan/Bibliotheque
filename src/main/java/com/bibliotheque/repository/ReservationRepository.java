package com.bibliotheque.repository;

import com.bibliotheque.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Méthodes custom possibles ici si besoin
}

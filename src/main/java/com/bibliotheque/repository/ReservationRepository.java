package com.bibliotheque.repository;

import com.bibliotheque.entity.Reservation;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAdherent(Adherent adherent);
    Optional<Reservation> findByAdherentAndExemplaireAndStatut(Adherent adherent, Exemplaire exemplaire, String statut);
    List<Reservation> findByStatut(String statut);
}

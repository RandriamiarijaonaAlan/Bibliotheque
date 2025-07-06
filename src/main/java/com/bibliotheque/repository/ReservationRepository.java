package com.bibliotheque.repository;

import com.bibliotheque.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAdherent_IdAdherent(Long idAdherent);
   
    List<Reservation> findByStatut(String statut);
}

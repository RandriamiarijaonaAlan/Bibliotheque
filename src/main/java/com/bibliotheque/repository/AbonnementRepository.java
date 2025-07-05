package com.bibliotheque.repository;
import com.bibliotheque.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByStatut(String statut);
}

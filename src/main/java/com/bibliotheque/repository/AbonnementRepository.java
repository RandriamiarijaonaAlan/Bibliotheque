package com.bibliotheque.repository;
import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.entity.Adherent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByStatut(String statut);
    List<Abonnement> findByAdherentAndStatut(Adherent adherent, String statut);

}

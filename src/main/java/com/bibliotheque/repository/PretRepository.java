// PretRepository.java
package com.bibliotheque.repository;

import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PretRepository extends JpaRepository<Pret, Long> {
    long countByAdherentAndStatut(Adherent adherent, String statut);
    List<Pret> findByStatut(String statut);
}


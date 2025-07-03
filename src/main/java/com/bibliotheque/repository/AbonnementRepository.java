package com.bibliotheque.repository;

import com.bibliotheque.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
}

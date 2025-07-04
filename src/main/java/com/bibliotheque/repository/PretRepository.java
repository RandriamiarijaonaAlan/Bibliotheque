package com.bibliotheque.repository;

import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    Optional<Pret> findByExemplaireAndDateRetourIsNull(Exemplaire exemplaire);
List<Pret> findByDateRetourIsNull();
}

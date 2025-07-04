package com.bibliotheque.repository;

import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Livre;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
List<Exemplaire> findByLivre(Livre livre);

}

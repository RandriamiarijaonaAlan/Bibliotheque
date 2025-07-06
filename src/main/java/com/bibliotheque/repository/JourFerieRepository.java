package com.bibliotheque.repository;

import com.bibliotheque.entity.JourFerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    boolean existsByDate(LocalDate date);
}

package com.bibliotheque.repository;

import com.bibliotheque.entity.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Long> { }

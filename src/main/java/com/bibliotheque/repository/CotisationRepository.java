// CotisationRepository.java
package com.bibliotheque.repository;

import com.bibliotheque.entity.Cotisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotisationRepository extends JpaRepository<Cotisation, Long> {}

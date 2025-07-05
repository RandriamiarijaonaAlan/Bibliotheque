// PretRepository.java
package com.bibliotheque.repository;

import com.bibliotheque.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PretRepository extends JpaRepository<Pret, Long> {}

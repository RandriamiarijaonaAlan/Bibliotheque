package com.bibliotheque.repository;

import com.bibliotheque.entity.TypeAdherent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeAdherentRepository extends JpaRepository<TypeAdherent, Long> {
    Optional<TypeAdherent> findByNomIgnoreCase(String nom);
    Optional<TypeAdherent> findByCode(Integer code);
}

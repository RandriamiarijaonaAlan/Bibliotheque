package com.bibliotheque.repository;

import com.bibliotheque.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer> {
    Profil findByEmail(String email);
     Optional<Profil> findByCodeAdmin(String codeAdmin);
}

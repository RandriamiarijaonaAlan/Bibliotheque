package com.bibliotheque.repository;
import com.bibliotheque.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfilRepository extends JpaRepository<Profil, Long> {
    Optional<Profil> findByEmail(String email);
}

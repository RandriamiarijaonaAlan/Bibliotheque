package com.bibliotheque.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bibliotheque.entity.Livre;
public interface LivreRepository extends JpaRepository<Livre, Long> {
    
}

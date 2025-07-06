package com.bibliotheque.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.repository.ExemplaireRepository;

@Service
public class ExemplaireService {

    private final ExemplaireRepository exemplaireRepository;

    public ExemplaireService(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    public List<Exemplaire> findAll() {
        return exemplaireRepository.findAll();
    }

    // Tu peux ajouter d'autres méthodes si besoin
}

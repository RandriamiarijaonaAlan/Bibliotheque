package com.bibliotheque.service;

import com.bibliotheque.dto.ActivityDto;
import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    public List<ActivityDto> getAllActivities() {
        List<ActivityDto> result = new ArrayList<>();

        // Récupérer uniquement les abonnements en attente
        List<Abonnement> demandesAbonnements = abonnementRepository.findByStatut("en_attente");

        for (Abonnement a : demandesAbonnements) {
            ActivityDto dto = new ActivityDto();
            dto.setId(a.getId());
            dto.setType("abonnement");
            dto.setNomAdherent(a.getAdherent().getProfil().getNom());
            dto.setTitreLivre("N/A"); // car c'est un abonnement
            dto.setDateDebut(a.getDateDebut());
            dto.setDateFin(a.getDateFin());
            result.add(dto);
        }

        // Tu peux plus tard ajouter les réservations ou prêts ici

        return result;
    }

    public void validerAbonnement(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id).orElse(null);
        if (abonnement != null && "en_attente".equals(abonnement.getStatut())) {
            abonnement.setStatut("valide");
            abonnementRepository.save(abonnement);
        }
    }

    // Méthodes suivantes à implémenter plus tard
    public void validerReservation(Long id) {}
    public void validerPret(Long id) {}
    public void validerProlongement(Long id) {}
}

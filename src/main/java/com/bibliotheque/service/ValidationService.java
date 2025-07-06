package com.bibliotheque.service;

import com.bibliotheque.dto.ActivityDto;
import com.bibliotheque.entity.Abonnement;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Pret;
import com.bibliotheque.repository.AbonnementRepository;
import com.bibliotheque.repository.PretRepository;
import jakarta.transaction.Transactional;
import com.bibliotheque.repository.ExemplaireRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
private ExemplaireRepository exemplaireRepository;


    public List<ActivityDto> getAllActivities() {
        List<ActivityDto> result = new ArrayList<>();

        // Abonnements en attente
        List<Abonnement> demandesAbonnements = abonnementRepository.findByStatut("en_attente");
        for (Abonnement a : demandesAbonnements) {
            ActivityDto dto = new ActivityDto();
            dto.setId(a.getId());
            dto.setType("abonnement");
            dto.setNomAdherent(a.getAdherent().getProfil().getNom());
            dto.setTitreLivre("N/A");
            dto.setDateDebut(a.getDateDebut());
            dto.setDateFin(a.getDateFin());
            result.add(dto);
        }

        // Prêts en attente
        List<Pret> pretsEnAttente = pretRepository.findByStatut("en_attente");
        for (Pret p : pretsEnAttente) {
            ActivityDto dto = new ActivityDto();
            dto.setId(p.getIdPret());
            dto.setType("pret");
            dto.setNomAdherent(p.getAdherent().getProfil().getNom());
            dto.setTitreLivre(p.getExemplaire().getLivre().getTitre());
            dto.setDateDebut(p.getDatePret()); // ou p.getDateDebut() si tu as cette info
            dto.setDateFin(p.getDateRetourPrevue());
            result.add(dto);
        }

        return result;
    }

    public void validerAbonnement(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id).orElse(null);
        if (abonnement != null && "en_attente".equals(abonnement.getStatut())) {
            abonnement.setStatut("valide");
            abonnementRepository.save(abonnement);
        }
    }

    @Transactional
public void validerPret(Long idPret) {
    Pret pret = pretRepository.findById(idPret)
        .orElseThrow(() -> new RuntimeException("Prêt non trouvé"));

    // Changer le statut du prêt
    pret.setStatut("valide");

    // Récupérer l'exemplaire lié
    Exemplaire exemplaire = pret.getExemplaire();

    // Vérifier l'état actuel de l'exemplaire
    if ("en_pret".equals(exemplaire.getEtat())) {
        exemplaire.setEtat("indisponible");  // passer à indisponible
    }

    // Sauvegarder les modifications
     exemplaireRepository.save(exemplaire);
     pretRepository.save(pret);
}

        public void refuserPret(Long id) {
        Pret pret = pretRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Prêt non trouvé"));
        pret.setStatut("refuse");
        pretRepository.save(pret);
    }

    // Si besoin, implémente validerReservation et validerProlongement plus tard
    public void validerReservation(Long id) {}
    public void validerProlongement(Long id) {}
}



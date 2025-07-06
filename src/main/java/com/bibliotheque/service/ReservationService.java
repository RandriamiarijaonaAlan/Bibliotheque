package com.bibliotheque.service;

import com.bibliotheque.entity.*;
import com.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired private AdherentRepository adherentRepo;
    @Autowired private ExemplaireRepository exemplaireRepo;
    @Autowired private ReservationRepository reservationRepo;
    @Autowired private AbonnementRepository abonnementRepo;
    @Autowired private JourFerieRepository jourFerieRepository;

    // Vérifie si l'adhérent a un abonnement valide
   public boolean estAbonne(Adherent adherent) {
    List<Abonnement> abonnementsValides = abonnementRepo.findByAdherentAndStatut(adherent, "valide");
    return abonnementsValides != null && !abonnementsValides.isEmpty();
}


    public String demanderReservation(Long idAdherent, Long idExemplaire, LocalDate dateDebut, LocalDate dateFin) {
        Optional<Adherent> adhOpt = adherentRepo.findById(idAdherent);
        if (adhOpt.isEmpty()) return "Adhérent non trouvé.";
        Adherent adherent = adhOpt.get();

        if (!"actif".equalsIgnoreCase(adherent.getStatut())) return "Adhérent non actif.";

        if (!estAbonne(adherent)) return "Adhérent non abonné.";

        if (adherent.getQuotaReservation() == null || adherent.getQuotaReservation() <= 0)
            return "Quota de réservation dépassé.";

        Optional<Exemplaire> exOpt = exemplaireRepo.findById(idExemplaire);
        if (exOpt.isEmpty()) return "Exemplaire non trouvé.";
        Exemplaire exemplaire = exOpt.get();

        if (!"disponible".equalsIgnoreCase(exemplaire.getEtat()))
            return "Exemplaire non disponible.";

        // Vérification jour férié pour dateDebut et dateFin
        boolean debutJourFerie = jourFerieRepository.existsByDate(dateDebut);
        boolean finJourFerie = jourFerieRepository.existsByDate(dateFin);
        if (debutJourFerie || finJourFerie)
            return "La date de début ou fin de réservation est un jour férié.";

        // Vérification contrainte âge par rapport au livre
        Profil profil = adherent.getProfil();
        if (profil == null) return "Profil adhérent introuvable.";
        int age = profil.getAgeEn2025();

        Livre livre = exemplaire.getLivre();
        if (livre == null) return "Livre introuvable.";
        Integer ageMin = livre.getAgeMin();
        if (ageMin != null && age < ageMin)
            return "Adhérent trop jeune (âge min: " + ageMin + ")";

        // Création de la réservation
        Reservation res = new Reservation();
        res.setAdherent(adherent);
        res.setExemplaire(exemplaire);
        res.setDateDebutReservation(dateDebut);
        res.setDateFinReservation(dateFin);
        res.setStatut("en_attente");
        reservationRepo.save(res);

        // Mise à jour du quota
        adherent.setQuotaReservation(adherent.getQuotaReservation() - 1);
        adherentRepo.save(adherent);

        return "Demande envoyée avec succès.";
    }
    public void validerReservation(Long idReservation) {
        // Trouver la réservation par son ID
        Reservation res = reservationRepo.findById(idReservation)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable : " + idReservation));

        // Modifier le statut
        res.setStatut("reservee");

        // Sauvegarder la modification
        reservationRepo.save(res);
    }
     public void refuserReservation(Long idReservation) {
        Reservation res = reservationRepo.findById(idReservation)
                .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable : " + idReservation));

        res.setStatut("refusee");
        reservationRepo.save(res);
    }
    public List<Reservation> getReservationsParAdherent(Long idAdherent) {
    return reservationRepo.findByAdherent_IdAdherent(idAdherent);
}

public List<Adherent> getAllAdherents() {
    return adherentRepo.findAll();
}


}

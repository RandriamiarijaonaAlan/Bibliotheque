package com.bibliotheque.service;

import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Pret;
import com.bibliotheque.repository.AdherentRepository;
import com.bibliotheque.repository.ExemplaireRepository;
import com.bibliotheque.repository.PretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public void creerPret(Long idAdherent, Long idExemplaire, LocalDate dateRetourPrevue, String typeLecture) throws Exception {
    Adherent adherent = adherentRepository.findById(idAdherent)
        .orElseThrow(() -> new Exception("Adhérent non trouvé"));

    Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
        .orElseThrow(() -> new Exception("Exemplaire non trouvé"));

    if (!"disponible".equalsIgnoreCase(exemplaire.getEtat())) {
        throw new Exception("Exemplaire non disponible pour un prêt");
    }

    Pret pret = new Pret();
    pret.setAdherent(adherent);
    pret.setExemplaire(exemplaire);
    pret.setDatePret(LocalDate.now()); // date du jour automatiquement
   // <- ici on utilise la date reçue
    pret.setDateRetourPrevue(dateRetourPrevue);
    pret.setTypeLecture(typeLecture);
    pret.setStatut("en_attente");

    exemplaire.setEtat("en_pret");
    exemplaireRepository.save(exemplaire);

    pretRepository.save(pret);
}

}

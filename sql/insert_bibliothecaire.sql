INSERT INTO profil (id_profil, nom, duree_pret_jours, duree_prolongement_jours, prolongement_max, quota_reservation, statut, email)
VALUES (2, 'bibliothécaire', 0, 0, 0, 0, 'actif', 'biblio@biblio.fr');

INSERT INTO adherent (id_adherent, nom, email, mot_de_passe, statut, id_profil, id_type_adherent)
VALUES (2, 'Bibliothécaire', 'biblio@biblio.fr', 'motdepasse', 'actif', 2, 3);

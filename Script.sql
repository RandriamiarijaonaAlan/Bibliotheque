-- filepath: d:\S4\naina\Bibliotheque\Script.sql
-- TABLE TYPE_ADHERENT
CREATE TABLE type_adherent (
    id_type_adherent SERIAL PRIMARY KEY,
    nom VARCHAR(100) UNIQUE NOT NULL
);

-- TABLE PROFIL (nouvelle)
CREATE TABLE profil (
    id_profil SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_de_naisaance DATE
);


CREATE TABLE adherent (
    id_adherent SERIAL PRIMARY KEY,
    id_type_adherent INT REFERENCES type_adherent(id_type_adherent),
    quota_reservation INT DEFAULT 2,
    duree_pret_jours INT DEFAULT 14,
    prolongement_max INT DEFAULT 2,
    duree_prolongement_jours INT DEFAULT 7,
    id_profil INT NOT NULL UNIQUE REFERENCES profil(id_profil),
    statut VARCHAR(20) CHECK (statut IN ('actif', 'inactif', 'blackliste')) DEFAULT 'inactif',
    date_inscription DATE DEFAULT CURRENT_DATE
);


-- TABLE COTISATIONS (modifiée avec validation)
CREATE TABLE cotisation (
    id_cotisation SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    type_adhesion VARCHAR(50),
    date_debut DATE,
    date_fin DATE,
    montant NUMERIC(10,2),
    payee BOOLEAN DEFAULT FALSE,
    valide_par INT REFERENCES adherent(id_adherent) -- bibliothécaire
);

-- TABLE LIVRES
CREATE TABLE livre (
    id_livre SERIAL PRIMARY KEY,
    titre VARCHAR(255),
    auteur VARCHAR(255),
    isbn VARCHAR(20) UNIQUE,
    langue VARCHAR(50),
    resume TEXT,
    mots_cles TEXT,
    classification VARCHAR(100)
);

-- TABLE EXEMPLAIRES
CREATE TABLE exemplaire (
    id_exemplaire SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id_livre),
    code_barre VARCHAR(50) UNIQUE,
    etat VARCHAR(20) CHECK (etat IN ('disponible', 'en_pret', 'en_reservation', 'lecture_sur_place')) DEFAULT 'disponible',
    localisation VARCHAR(100),
    autorise_lecture_sur_place BOOLEAN DEFAULT TRUE
);

-- TABLE PRÊTS
CREATE TABLE pret (
    id_pret SERIAL PRIMARY KEY,
    id_exemplaire INT REFERENCES exemplaire(id_exemplaire),
    id_adherent INT REFERENCES adherent(id_adherent),
    date_pret DATE,
    date_retour_prevue DATE,
    date_retour_reelle DATE,
    type_lecture VARCHAR(20) CHECK (type_lecture IN ('domicile', 'sur_place')),
    prolongements INT DEFAULT 0
);

-- TABLE PROLONGEMENTS
CREATE TABLE prolongement (
    id_prolongement SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id_pret),
    date_prolongement DATE DEFAULT CURRENT_DATE,
    nouvelle_date_retour DATE
);

-- TABLE RÉSERVATIONS
CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    id_exemplaire INT REFERENCES exemplaire(id_exemplaire),
    id_adherent INT REFERENCES adherent(id_adherent),
    date_reservation DATE DEFAULT CURRENT_DATE,
    statut VARCHAR(20) CHECK (statut IN ('active', 'terminee', 'expiree')) DEFAULT 'active'
);

-- TABLE PÉNALITÉS
CREATE TABLE penalite (
    id_penalite SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    id_pret INT REFERENCES pret(id_pret),
    montant NUMERIC(10,2),
    raison TEXT,
    reglee BOOLEAN DEFAULT FALSE,
    date_penalite DATE DEFAULT CURRENT_DATE
);

-- TABLE JOURS FÉRIÉS
CREATE TABLE jour_ferie (
    id_jour SERIAL PRIMARY KEY,
    date DATE UNIQUE,
    description VARCHAR(255),
    politique_retour VARCHAR(10) CHECK (politique_retour IN ('avant', 'apres')) DEFAULT 'apres'
);

-- TABLE ÉVALUATIONS
CREATE TABLE evaluation (
    id_evaluation SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id_livre),
    id_adherent INT REFERENCES adherent(id_adherent),
    note INT CHECK (note BETWEEN 1 AND 5),
    commentaire TEXT,
    date_evaluation DATE DEFAULT CURRENT_DATE,
    UNIQUE (id_livre, id_adherent)
);

-- TABLE BLACKLIST
CREATE TABLE blacklist (
    id_blacklist SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    raison TEXT,
    date_debut DATE,
    date_fin DATE
);

-- TABLE TAGS
CREATE TABLE tag_livre (
    id_tag SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id_livre),
    tag VARCHAR(100)
);

-- TABLE NOTIFICATIONS
CREATE TABLE notification (
    id_notification SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    type VARCHAR(100),
    contenu TEXT,
    date_envoi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- TABLE ABONNEMENT
CREATE TABLE abonnement (
    id_abonnement SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    statut VARCHAR(20) CHECK (statut IN ('actif', 'expiré', 'en_attente')) DEFAULT 'en_attente'
);

-- Insertion des types d'adhérents
INSERT INTO type_adherent (nom) VALUES
('étudiant'),
('professeur'),
('bibliothécaire'),
('professionnel');

-- Insertion des adhérents (adapter pour utiliser id_type_adherent)
INSERT INTO adherent (
    id_type_adherent,
    quota_reservation,
    duree_pret_jours,
    prolongement_max,
    duree_prolongement_jours,
    id_profil,
    statut,
    date_inscription
) VALUES
(1, 3, 21, 2, 7, 1, 'actif', CURRENT_DATE),
(2, 5, 30, 3, 10, 2, 'actif', CURRENT_DATE),
(3, 10, 60, 5, 15, 3, 'actif', CURRENT_DATE),
(4, 4, 14, 2, 7, 4, 'actif', CURRENT_DATE);

<select name="profilId" required>
  <option th:each="p : ${profils}" th:value="${p.idProfil}" th:text="${p.nom}"></option>
</select>
<select name="typeAdherentId" required>
  <option th:each="t : ${typesAdherent}" th:value="${t.idTypeAdherent}" th:text="${t.nom}"></option>
</select>

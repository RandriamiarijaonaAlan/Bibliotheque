-- FILE: Script_Corrige_Bibliotheque.sql

-- TABLE TYPE_ADHERENT
CREATE TABLE type_adherent (
    id_type_adherent SERIAL PRIMARY KEY,
    nom VARCHAR(100) UNIQUE NOT NULL,
    code INT -- Pour le login admin, mais partagé (voir note plus bas)
);

-- TABLE PROFIL (corrigée avec gestion du code admin individuel)
CREATE TABLE profil (
    id_profil SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_de_naissance DATE NOT NULL,
    code_admin CHAR(4), -- Seulement pour les bibliothécaires
    CONSTRAINT ck_code_admin CHECK (code_admin ~ '^[0-9]{4}$' OR code_admin IS NULL)
);

-- TABLE ADHERENT
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

-- TABLE COTISATION
CREATE TABLE cotisation (
    id_cotisation SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    type_adhesion VARCHAR(50),
    date_debut DATE,
    date_fin DATE,
    montant NUMERIC(10,2),
    payee BOOLEAN DEFAULT FALSE,
    valide_par INT REFERENCES adherent(id_adherent) -- Bibliothécaire
);

-- TABLE LIVRE
CREATE TABLE livre (
    id_livre SERIAL PRIMARY KEY,
    titre VARCHAR(255),
    auteur VARCHAR(255),
    isbn VARCHAR(20) UNIQUE,
    langue VARCHAR(50),
    resume TEXT,
    mots_cles TEXT,
    classification VARCHAR(100),
    age_min INT -- Âge minimal requis
);

-- TABLE EXEMPLAIRE
CREATE TABLE exemplaire (
    id_exemplaire SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id_livre),
    titre_exemplaire VARCHAR(255),
    code_barre VARCHAR(50) UNIQUE,
    etat VARCHAR(20) CHECK (etat IN ('disponible', 'en_pret', 'en_reservation', 'lecture_sur_place')) DEFAULT 'disponible',
    localisation VARCHAR(100),
    autorise_lecture_sur_place BOOLEAN DEFAULT TRUE
);

-- TABLE PRET
CREATE TABLE pret (
    id_pret SERIAL PRIMARY KEY,
    id_exemplaire INT REFERENCES exemplaire(id_exemplaire),
    id_adherent INT REFERENCES adherent(id_adherent),
    date_pret DATE,
    date_retour_prevue DATE,
    date_retour_reelle DATE,
    type_lecture VARCHAR(20) CHECK (type_lecture IN ('domicile', 'sur_place')),
    prolongements INT DEFAULT 0,
    statut VARCHAR(20) CHECK (statut IN ('en_attente','valide','refuse')) DEFAULT 'en_attente'
);

-- TABLE PROLONGEMENT
CREATE TABLE prolongement (
    id_prolongement SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id_pret),
    date_prolongement DATE DEFAULT CURRENT_DATE,
    nouvelle_date_retour DATE,
    statut VARCHAR(20) CHECK (statut IN ('en_attente','valide','refuse')) DEFAULT 'en_attente'
);

-- TABLE RESERVATION
CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    id_exemplaire INT REFERENCES exemplaire(id_exemplaire),
    id_adherent INT REFERENCES adherent(id_adherent),
    date_reservation DATE DEFAULT CURRENT_DATE,
    statut VARCHAR(20) CHECK (statut IN ('en_attente', 'reservee', 'terminee', 'expiree')) DEFAULT 'en_attente'
);

-- TABLE PENALITE
CREATE TABLE penalite (
    id_penalite SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id_adherent),
    id_pret INT REFERENCES pret(id_pret),
    montant NUMERIC(10,2),
    raison TEXT,
    reglee BOOLEAN DEFAULT FALSE,
    date_penalite DATE DEFAULT CURRENT_DATE
);

-- TABLE JOUR FERIE
CREATE TABLE jour_ferie (
    id_jour SERIAL PRIMARY KEY,
    date DATE UNIQUE,
    description VARCHAR(255),
    politique_retour VARCHAR(10) CHECK (politique_retour IN ('avant', 'apres')) DEFAULT 'apres'
);

-- TABLE EVALUATION
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

-- TABLE TAG LIVRE
CREATE TABLE tag_livre (
    id_tag SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id_livre),
    tag VARCHAR(100)
);

-- TABLE NOTIFICATION
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
    statut VARCHAR(20) CHECK (statut IN ('en_attente', 'actif', 'expire', 'refuse')) DEFAULT 'en_attente'
);

-- VUE : Historique activité (réservation, prêt, prolongement)
CREATE VIEW vue_historique_activite AS
SELECT p.id_pret        AS id_activite, p.id_adherent, p.id_exemplaire, p.date_pret       AS date_action, 'pret'        AS type
  FROM pret p
UNION ALL
SELECT r.id_reservation AS id_activite, r.id_adherent, r.id_exemplaire, r.date_reservation AS date_action, 'reservation' AS type
  FROM reservation r
UNION ALL
SELECT pr.id_prolongement, pt.id_adherent, pt.id_exemplaire, pr.date_prolongement          AS date_action, 'prolongement' AS type
  FROM prolongement pr JOIN pret pt ON pt.id_pret = pr.id_pret;

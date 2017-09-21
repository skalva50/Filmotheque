--create database filmotheque;

CREATE TABLE fichier
(
  id serial NOT NULL,
  chemin character varying(500),
  CONSTRAINT prk_constraint_fichier PRIMARY KEY (id),
  CONSTRAINT unique_constraint_operation UNIQUE (chemin)
);

CREATE TABLE GENRE
(
	id serial NOT NULL,
	idTMDB Integer,
	libelle varchar(255),
	CONSTRAINT prk_constraint_genre PRIMARY KEY (id),
	CONSTRAINT unique_constraint_genre UNIQUE (idTMDB)
);

CREATE TABLE SEXE
(
	id serial NOT NULL,
	typeSexe varchar(10),
	CONSTRAINT prk_constraint_sexe PRIMARY KEY (id)
);

insert into SEXE (typeSexe) values ('Femme');
insert into SEXE (typeSexe) values ('Homme');


CREATE TABLE PAYS
(
	id serial NOT NULL,
	idIso varchar(10),
	Nom varchar(255),
	CONSTRAINT prk_constraint_pays PRIMARY KEY (id),
	CONSTRAINT unique_constraint_pays UNIQUE (idIso)
);

CREATE TABLE ACTEUR
(
	id serial NOT NULL,
	idTMDB Integer,
	nom varchar(255),
	photo varchar(255),
	biographie varchar(5000),
	dateNaissance date,
	dateDeces date,
	lieuNaissance varchar(255),
	idSexe Integer,
	popularite float,
	CONSTRAINT prk_constraint_personnage PRIMARY KEY (id),
	CONSTRAINT unique_constraint_personnage UNIQUE (idTMDB),
	CONSTRAINT fk_ACTEUR FOREIGN KEY (idSexe)
		REFERENCES Sexe(id)
);

CREATE TABLE REALISATEUR
(
	id serial NOT NULL,
	idTMDB Integer,
	nom varchar(255),
	photo varchar(255),
	biographie varchar(5000),
	dateNaissance date,
	dateDeces date,
	lieuNaissance varchar(255),
	idSexe Integer,
	popularite float,
	CONSTRAINT prk_constraint_REALISATEUR PRIMARY KEY (id),
	CONSTRAINT unique_constraint_REALISATEUR UNIQUE (idTMDB),
	CONSTRAINT fk_REALISATEUR FOREIGN KEY (idSexe)
		REFERENCES Sexe(id)
);

CREATE TABLE MediaTMDB
(
	id serial NOT NULL,
	idTMDB Integer,
	titre varchar(255),
	titreOriginal varchar(255),
	resume varchar(5000),
	affiche varchar(255),
	cleVideo varchar(255),
	siteVideo varchar(255),
	resumeCourt varchar(500),
	dateSortie Date,
	dateAjout Date,
	CONSTRAINT prk_constraint_MEDIA PRIMARY KEY (id)
);

CREATE TABLE VIDEO
(
	id serial NOT NULL,
	nom varchar(255),
	cleVideo varchar(255),
	siteVideo varchar(255),
	idMedia Integer,
	CONSTRAINT prk_constraint_VIDEO PRIMARY KEY (id),
	CONSTRAINT fk_VIDEO_MEDIA FOREIGN KEY (idMedia)
		REFERENCES MediaTMDB(id)
);


CREATE TABLE FILM
(
	id serial NOT NULL,	
	idFichier Integer,
	popularite float,
	note float,
	duree Integer,
	CONSTRAINT prk_constraint_film PRIMARY KEY (id),	
	CONSTRAINT fk_film_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id) ,
	CONSTRAINT fk_film_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
);

CREATE TABLE FILM_GENRE
(
	idFilm Integer,
	idGenre Integer,
	CONSTRAINT fk_FILM_GENRE_idFilm FOREIGN KEY (idFilm)
		REFERENCES FILM(id),
	CONSTRAINT fk_FILM_GENRE_idGenre FOREIGN KEY (idGenre)
		REFERENCES GENRE(id)
);

CREATE TABLE FILM_PERSONNAGE
(
	id serial NOT NULL,
	idFilm Integer,
	idActeur Integer,
	nom varchar(255),
	CONSTRAINT prk_constraint_FILM_PERSONNAGE PRIMARY KEY (id),
	CONSTRAINT fk_FILM_personnage_idFilm FOREIGN KEY (idFilm)
		REFERENCES FILM(id),
	CONSTRAINT fk_FILM_personnage_idActeur FOREIGN KEY (idActeur)
		REFERENCES Acteur(id)
);

CREATE TABLE FILM_REALISATEUR
(
	idFilm Integer,
	idREALISATEUR Integer,
	CONSTRAINT fk_FILM_REALISATEUR_idFilm FOREIGN KEY (idFilm)
		REFERENCES FILM(id),
	CONSTRAINT fk_FILM_REALISATEUR_idREALISATEUR FOREIGN KEY (idREALISATEUR)
		REFERENCES REALISATEUR(id)
);


CREATE TABLE SERIE
(
	id serial NOT NULL,	
	popularite float,
	note float,
	CONSTRAINT prk_constraint_serie PRIMARY KEY (id),	
	CONSTRAINT fk_SERIE_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
 
);

CREATE TABLE SERIE_GENRE
(
	idSerie Integer,
	idGenre Integer,
	CONSTRAINT fk_SERIE_GENRE_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id),
	CONSTRAINT fk_SERIE_GENRE_idGenre FOREIGN KEY (idGenre)
		REFERENCES GENRE(id)
);

CREATE TABLE SERIE_PERSONNAGE
(
	id serial NOT NULL,
	idSerie Integer,
	idActeur Integer,
	nom varchar(255),
	CONSTRAINT prk_constraint_SERIE_PERSONNAGE PRIMARY KEY (id),
	CONSTRAINT fk_SERIE_personnage_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id),
	CONSTRAINT fk_SERIE_personnage_idActeur FOREIGN KEY (idActeur)
		REFERENCES Acteur(id)
);


CREATE TABLE SERIE_REALISATEUR
(
	idSERIE Integer,
	idREALISATEUR Integer,
	CONSTRAINT fk_SERIE_REALISATEUR_idSERIE FOREIGN KEY (idSERIE)
		REFERENCES SERIE(id),
	CONSTRAINT fk_FILM_REALISATEUR_idREALISATEUR FOREIGN KEY (idREALISATEUR)
		REFERENCES REALISATEUR(id)
);

CREATE TABLE MEDIA_PAYS
(
	idMedia Integer,
	idPays Integer,
	CONSTRAINT fk_MEDIA_PAYS_idMedia FOREIGN KEY (idMedia)
		REFERENCES MediaTMDB(id),
	CONSTRAINT fk_MEDIA_PAYS_idPays FOREIGN KEY (idPays)
		REFERENCES PAYS(id)
);


CREATE TABLE SAISON
(
	id serial NOT NULL,	
	idSerie integer,	
	numero Integer,	
	CONSTRAINT prk_constraint_SAISON PRIMARY KEY (id),
	CONSTRAINT unique_constraint_saison UNIQUE (idSerie,numero),
	CONSTRAINT fk_film_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id),
	CONSTRAINT fk_Saison_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
	
 
);

CREATE TABLE EPISODE
(
	id serial NOT NULL,	
	idFichier Integer,
	idSaison Integer,	
	numero integer,
	CONSTRAINT prk_constraint_episode PRIMARY KEY (id),	
	CONSTRAINT fk_episode_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id),
	CONSTRAINT fk_episode_idSaison FOREIGN KEY (idSaison)
		REFERENCES saison(id) ,
	CONSTRAINT fk_SERIE_id FOREIGN KEY (id)
		REFERENCES MediaTMDB(id) 
);

-- Ceation USER
create table APP_USER (
   id serial NOT NULL,
   identifiant VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   prenom VARCHAR(30) NOT NULL,
   nom  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   state VARCHAR(30) NOT NULL,  
   PRIMARY KEY (id),
   UNIQUE (identifiant)
);
  
/* USER_PROFILE table contains all possible roles */
create table USER_PROFILE(
   id serial NOT NULL,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
  
/* JOIN TABLE for MANY-TO-MANY relationship*/ 
CREATE TABLE APP_USER_USER_PROFILE (	
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);
 
/* Populate USER_PROFILE Table */
 
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
 
INSERT INTO USER_PROFILE(type)
VALUES ('INSCRIT');
 
/* Populate APP_USER Table */
INSERT INTO APP_USER(identifiant, password, prenom, nom, email, state)
VALUES ('olivier','olivier', 'Olivier','lecornu','olivier@xyz.com', 'Active');
 
INSERT INTO APP_USER(identifiant, password, prenom, nom, email, state)
VALUES ('laurie','laurie', 'Laurie','Fabre','laurie@xyz.com', 'Active');

 
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
(
  SELECT usr.id, profile.id FROM app_user usr, user_profile profile  
  where usr.identifiant='olivier' and profile.type='ADMIN'
 );

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
(
  SELECT usr.id, profile.id FROM app_user usr, user_profile profile  
  where usr.identifiant='olivier' and profile.type='INSCRIT'
 );

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
(
  SELECT usr.id, profile.id FROM app_user usr, user_profile profile
  where usr.identifiant='laurie' and profile.type='INSCRIT'
);
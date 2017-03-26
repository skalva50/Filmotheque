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

CREATE TABLE ACTEUR
(
	id serial NOT NULL,
	idTMDB Integer,
	nom varchar(255),
	photo varchar(255),
	CONSTRAINT prk_constraint_personnage PRIMARY KEY (id),
	CONSTRAINT unique_constraint_personnage UNIQUE (idTMDB)
);

CREATE TABLE REALISATEUR
(
	id serial NOT NULL,
	idTMDB Integer,
	nom varchar(255),
	photo varchar(255),
	CONSTRAINT prk_constraint_REALISATEUR PRIMARY KEY (id),
	CONSTRAINT unique_constraint_REALISATEUR UNIQUE (idTMDB)
);

CREATE TABLE FILM
(
	id serial NOT NULL,
	idTMDB Integer,
	idFichier Integer,
	titre varchar(255),
	titreOriginal varchar(255),
	resume varchar(5000),
	affiche varchar(255),
	cleVideo varchar(255),
	siteVideo varchar(255),
	popularite float,
	note float,
	resumeCourt varchar(500),
	dateSortie varchar(255),
	CONSTRAINT prk_constraint_film PRIMARY KEY (id),	
	CONSTRAINT fk_film_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id) 
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
	idTMDB Integer,	
	titre varchar(255),
	titreOriginal varchar(255),
	resume varchar(5000),
	affiche varchar(255),
	popularite float,
	note float,
	cleVideo varchar(255),
	siteVideo varchar(255),
	resumeCourt varchar(500),
	dateSortie varchar(255),
	CONSTRAINT prk_constraint_serie PRIMARY KEY (id),
	CONSTRAINT unique_constraint_serie UNIQUE (idTMDB)	
 
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

CREATE TABLE SAISON
(
	id serial NOT NULL,
	idTMDB Integer,
	idSerie integer,
	titre varchar(255),
	titreOriginal varchar(255),		
	resume varchar(5000),
	affiche varchar(255),
	dateSortie varchar(255),
	numero Integer,
	cleVideo varchar(255),
	siteVideo varchar(255),
	resumeCourt varchar(500),
	CONSTRAINT prk_constraint_SAISON PRIMARY KEY (id),
	CONSTRAINT unique_constraint_saison UNIQUE (idSerie,numero),
	CONSTRAINT fk_film_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id) 
 
);

CREATE TABLE EPISODE
(
	id serial NOT NULL,
	idTMDB Integer,
	idFichier Integer,
	idSaison Integer,
	titre varchar(255),
	titreOriginal varchar(255),
	resume varchar(5000),
	affiche varchar(255),
	cleVideo varchar(255),
	siteVideo varchar(255),
	numero integer,
	resumeCourt varchar(500),
	dateSortie varchar(255),
	CONSTRAINT prk_constraint_episode PRIMARY KEY (id),	
	CONSTRAINT fk_episode_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id),
	CONSTRAINT fk_episode_idSaison FOREIGN KEY (idSaison)
		REFERENCES saison(id)  
);




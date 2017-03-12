create database filmotheque;

CREATE TABLE fichier
(
  id serial NOT NULL,
  chemin character varying(500),
  CONSTRAINT prk_constraint_fichier PRIMARY KEY (id),
  CONSTRAINT unique_constraint_operation UNIQUE (chemin)
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
	CONSTRAINT prk_constraint_film PRIMARY KEY (id),	
	CONSTRAINT fk_film_idFichier FOREIGN KEY (idFichier)
		REFERENCES fichier(id) 
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
	CONSTRAINT prk_constraint_serie PRIMARY KEY (id),
	CONSTRAINT unique_constraint_serie UNIQUE (idTMDB)	
 
);

CREATE TABLE SAISON
(
	id serial NOT NULL,
	idSerie integer,		
	resume varchar(5000),
	affiche varchar(255),
	dateSortie varchar(255),
	numero Integer,
	CONSTRAINT prk_constraint_SAISON PRIMARY KEY (id),
	CONSTRAINT unique_constraint_saison UNIQUE (idSerie,numero),
	CONSTRAINT fk_film_idSerie FOREIGN KEY (idSerie)
		REFERENCES SERIE(id) 
 
);







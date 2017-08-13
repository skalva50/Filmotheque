package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.tmdbObject.Cast;

public interface IFilmPersonnageService extends IService<Serializable, FilmPersonnage>{	
	FilmPersonnage getFilmPersonnagebyFilmActeur(Film film, Acteur acteur);
	FilmPersonnage castToFilmPersonnage(Cast cast, Film film);
}

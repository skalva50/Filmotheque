package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;

public interface IFilmPersonnageDao extends IDao<Serializable, FilmPersonnage> {	
	public FilmPersonnage getFilmPersonnagebyFilmActeur(Film film, Acteur acteur);	
}

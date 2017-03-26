package com.skalvasociety.skalva.dao;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;

public interface IFilmPersonnageDao {
	public void saveFilmPersonnage(FilmPersonnage filmPersonnage);
	public FilmPersonnage getFilmPersonnagebyFilmActeur(Film film, Acteur acteur);	
}

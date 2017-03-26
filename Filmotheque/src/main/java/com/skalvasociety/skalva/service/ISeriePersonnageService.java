package com.skalvasociety.skalva.service;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.tmdbObject.Cast;

public interface ISeriePersonnageService {
	void saveSeriePersonnage(SeriePersonnage seriePersonnage);
	SeriePersonnage getSeriePersonnagebySerieActeur(Serie serie, Acteur acteur);
	SeriePersonnage castToSeriePersonnage(Cast cast, Serie serie);
}

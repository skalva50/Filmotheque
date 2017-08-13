package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.tmdbObject.Cast;

public interface ISeriePersonnageService extends IService<Serializable, SeriePersonnage>{
	SeriePersonnage getSeriePersonnagebySerieActeur(Serie serie, Acteur acteur);
	SeriePersonnage castToSeriePersonnage(Cast cast, Serie serie);
}

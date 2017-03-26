package com.skalvasociety.skalva.dao;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;

public interface ISeriePersonnageDao {
	public void saveSeriePersonnage(SeriePersonnage seriePersonnage);
	public SeriePersonnage getSeriePersonnagebySerieActeur(Serie serie, Acteur acteur);
}

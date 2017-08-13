package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;

public interface ISeriePersonnageDao extends IDao<Serializable, SeriePersonnage> {	
	public SeriePersonnage getSeriePersonnagebySerieActeur(Serie serie, Acteur acteur);
}

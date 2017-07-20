package com.skalvasociety.skalva.dao;

import java.io.Serializable;
import java.util.List;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;

public interface ISaisonDao extends IDao<Serializable, Saison> {	
	public List<Saison> getSaisonByIdSerie(Serie serie);
	public Saison getSaisonByIdSerieNumSaison(Serie serie, Integer numSaison);
	public boolean isUnique(Saison saison);	
}

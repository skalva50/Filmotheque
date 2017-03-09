package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;

public interface ISaisonDao {
	void saveSaison(Saison saison);
	public List<Saison> getSaisonByIdSerie(Serie serie);
	public Saison getSaisonByIdSerieNumSaison(Serie serie, Integer numSaison);
	public boolean isUnique(Saison saison);
}

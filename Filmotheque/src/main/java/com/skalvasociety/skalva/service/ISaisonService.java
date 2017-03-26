package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.tmdbObject.SerieSaisonDetails;

public interface ISaisonService {
	public void saveSaison(Saison saison);
	public List<Saison> getSaisonByIdSerie(Serie serie);
	public Saison getSaisonById(Integer idSaison);
	void  serieSaisonDetailstoSaison(SerieSaisonDetails serieSaisonDetails, Saison saison);
	Saison getSaisonByIdSerieNumSaison(Serie serie, Integer numSaison);
}

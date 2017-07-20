package com.skalvasociety.skalva.service;

import java.io.Serializable;
import java.util.List;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.tmdbObject.SerieSaisonDetails;

public interface ISaisonService extends IService<Serializable, Saison> {	
	public List<Saison> getSaisonByIdSerie(Serie serie);	
	void  serieSaisonDetailstoSaison(SerieSaisonDetails serieSaisonDetails, Saison saison);
	Saison getSaisonByIdSerieNumSaison(Serie serie, Integer numSaison);
}

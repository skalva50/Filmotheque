package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;

public interface ISaisonService {
	public void saveSaison(Saison saison);
	public List<Saison> getSaisonByIdSerie(Serie serie);
}

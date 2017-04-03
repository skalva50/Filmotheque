package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.tmdbObject.Crew;

public interface IRealisateurService {
	public Realisateur crewToRealisateur(Crew crew); 
	public Realisateur getRealisateurById (Integer id);
	public List<Realisateur> getAll();
}

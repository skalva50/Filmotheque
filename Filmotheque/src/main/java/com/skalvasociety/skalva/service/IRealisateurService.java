package com.skalvasociety.skalva.service;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.tmdbObject.Crew;

public interface IRealisateurService {
	public Realisateur crewToRealisateur(Crew crew); 
}

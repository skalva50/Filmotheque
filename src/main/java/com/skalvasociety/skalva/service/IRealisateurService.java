package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.tmdbObject.Crew;

public interface IRealisateurService extends IService<Serializable, Realisateur> {
	public Realisateur crewToRealisateur(Crew crew);
	public void deleteRealisateurObsolete();
}

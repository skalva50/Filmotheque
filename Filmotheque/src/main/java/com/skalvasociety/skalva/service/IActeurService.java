package com.skalvasociety.skalva.service;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.tmdbObject.Cast;

public interface IActeurService {
	void saveActeur(Acteur acteur);
	Acteur getActeurById(Integer id);
	Acteur getActeurIdTMDB(Integer idTmdb);
	Acteur castToActeur(Cast cast);
}

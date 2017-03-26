package com.skalvasociety.skalva.service;

import com.skalvasociety.skalva.bean.Acteur;

public interface IActeurService {
	void saveActeur(Acteur acteur);
	Acteur getActeurIdTMDB(Integer idTmdb);
}

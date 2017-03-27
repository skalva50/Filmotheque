package com.skalvasociety.skalva.dao;

import com.skalvasociety.skalva.bean.Acteur;

public interface IActeurDao {
	void saveActeur(Acteur acteur);
	Acteur getActeurIdTMDB(Integer idTmdb);
	Acteur getActeurById(Integer id);
}

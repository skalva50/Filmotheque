package com.skalvasociety.skalva.dao;

import com.skalvasociety.skalva.bean.Realisateur;

public interface IRealisateurDao {
	void saveRealisateur(Realisateur realisateur);
	Realisateur getRealisateurByIdTMDB(Integer idTMDB);
}

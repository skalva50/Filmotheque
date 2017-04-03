package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Realisateur;

public interface IRealisateurDao {
	void saveRealisateur(Realisateur realisateur);
	Realisateur getRealisateurByIdTMDB(Integer idTMDB);
	Realisateur getRealisateurById(Integer id); 
	List<Realisateur> getAll();
}

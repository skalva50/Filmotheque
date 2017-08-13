package com.skalvasociety.skalva.dao;

import java.io.Serializable;
import java.util.List;

import com.skalvasociety.skalva.bean.Realisateur;

public interface IRealisateurDao extends IDao<Serializable, Realisateur> {	
	Realisateur getRealisateurByIdTMDB(Integer idTMDB);	
	public List<Realisateur> getAllOrderByNbFilms();	
}

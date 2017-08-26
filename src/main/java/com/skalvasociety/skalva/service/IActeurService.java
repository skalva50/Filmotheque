package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.tmdbObject.Cast;

public interface IActeurService extends IService<Serializable, Acteur>{
	Acteur getActeurIdTMDB(Integer idTmdb);
	Acteur castToActeur(Cast cast);
	public void deleteActeurObsolete();
}

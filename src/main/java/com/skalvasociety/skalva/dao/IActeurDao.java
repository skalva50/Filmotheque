package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Acteur;

public interface IActeurDao extends IDao<Serializable,Acteur>{	
	Acteur getActeurIdTMDB(Integer idTmdb);	
}

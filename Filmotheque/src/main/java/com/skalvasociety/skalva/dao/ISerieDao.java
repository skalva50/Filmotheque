package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Serie;


public interface ISerieDao extends IDao<Serializable, Serie>{
	public Serie getSerieByIdTMDB(Integer idTMDB);
	public boolean idTMDBExists(Serie serie);
}

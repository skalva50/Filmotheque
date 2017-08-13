package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Genre;

public interface IGenreDao extends IDao<Serializable, Genre> {	
	Genre getGenreByIdTmdb(Integer idTmdb);
}

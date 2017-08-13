package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Genre;


public interface IGenreService extends IService<Serializable, Genre>{	
	Genre getGenreByIdTmdb(Integer idTmdb);
	void majGenre();
}

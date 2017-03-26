package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Genre;

public interface IGenreService {
	void saveGenre(Genre genre);
	List<Genre> getGenres();
	Genre getGenreByIdTmdb(Integer idTmdb);
	void majGenre();
}

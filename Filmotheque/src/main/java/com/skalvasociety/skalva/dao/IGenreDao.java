package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Genre;

public interface IGenreDao {
	void saveGenre(Genre genre);
	List<Genre> getGenres();
	Genre getGenreById(int id);
	Genre getGenreByIdTmdb(Integer idTmdb);
}

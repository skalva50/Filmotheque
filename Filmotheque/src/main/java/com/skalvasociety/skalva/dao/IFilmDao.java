package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;


public interface IFilmDao {
	void saveFilm(Film film);
	List<Film> findAllFilms();
	Film getFilmById(Integer idFilm);

}

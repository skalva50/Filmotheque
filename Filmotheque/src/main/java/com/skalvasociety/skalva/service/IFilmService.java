package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;

public interface IFilmService {
	void saveFilm (Film film);
	List<Film> findAllFilms();
	Film getFilmById(Integer idFilm);
}

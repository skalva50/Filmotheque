package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FiltreFilm;
import com.skalvasociety.skalva.daoTools.PageRequest;


public interface IFilmDao {
	void saveFilm(Film film);
	Film getFilmById(Integer idFilm);
	List<Film> getFilmsPage(PageRequest<Film> pageRequest);
	public List<Film> getFilmsByRealisateurPage (PageRequest<Film> pageRequest , Integer idRealisateur);
	public List<Film> getFilmsByGenrePage(PageRequest<Film> pageRequest, FiltreFilm filtre);
}

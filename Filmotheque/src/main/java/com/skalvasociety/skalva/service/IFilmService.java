package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FiltreFilm;
import com.skalvasociety.skalva.daoTools.PageRequest;

public interface IFilmService {
	void saveFilm (Film film);
	List<Film> getFilmsPage(PageRequest<Film> pageRequest);
	Film getFilmById(Integer idFilm);
	public String getDureeFormatee(Film film);	
	public List<Film> getFilmByRealisateur (PageRequest<Film> pageRequest, Integer idRealisateur);
	public List<Film> getFilmByFiltrePage(PageRequest<Film> pageRequest, FiltreFilm filtre);
}

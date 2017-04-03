package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.daoTools.PageRequest;

public interface IFilmService {
	void saveFilm (Film film);
	List<Film> getFilmsPage(PageRequest pageRequest);
	Film getFilmById(Integer idFilm);
	public String getDureeFormatee(Film film);
	public int getTotalPage(PageRequest pageRequest);
	public List<Film> getFilmByRealisateur (PageRequest pageRequest, Integer idRealisateur);
}

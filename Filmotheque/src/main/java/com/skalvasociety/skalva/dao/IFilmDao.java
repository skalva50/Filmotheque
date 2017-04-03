package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.daoTools.PageRequest;


public interface IFilmDao {
	void saveFilm(Film film);
	Film getFilmById(Integer idFilm);
	List<Film> getFilmPage(PageRequest pageRequest);
	int getTotalPage(PageRequest pageRequest);
	public List<Film> getFilmByRealisateur (PageRequest pageRequest , Integer idRealisateur);
}

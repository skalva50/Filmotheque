package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.dao.IFilmDao;

@Service("filmService")
@Transactional
public class FilmService implements IFilmService {
	
	@Autowired
	private IFilmDao dao;

	public void saveFilm(Film film) {
		dao.saveFilm(film);
	}

	public List<Film> findAllFilms() {		
		return dao.findAllFilms();
	}

	public Film getFilmById(Integer idFilm) {		
		return dao.getFilmById(idFilm);
	}

}

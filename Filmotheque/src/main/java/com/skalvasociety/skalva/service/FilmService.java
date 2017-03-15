package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.Genre;
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
		List<Film> films = dao.findAllFilms();
		for (Film film : films) {
			List<Genre> genres = film.getGenres();
			for (Genre genre : genres) {
				System.out.println("Film titre: " + film.getTitre());
				System.out.println("Genres: " + genre.getLibelle());
			}

		}
		
		return films;
	}

	public Film getFilmById(Integer idFilm) {		
		return dao.getFilmById(idFilm);
	}

}

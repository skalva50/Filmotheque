package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.dao.IFilmDao;
import com.skalvasociety.skalva.daoTools.PageRequest;

@Service("filmService")
@Transactional
public class FilmService implements IFilmService {
	
	@Autowired
	private IFilmDao dao;

	public void saveFilm(Film film) {
		dao.saveFilm(film);
	}

	public List<Film> getFilmsPage(PageRequest pageRequest) {		
		List<Film> films = dao.getFilmPage(pageRequest);	
		return films;
	}

	public Film getFilmById(Integer idFilm) {		
		return dao.getFilmById(idFilm);
	}
	
	public String getDureeFormatee(Film film){
		String dureeFormatee = null;
		if(film != null && film.getDuree() != null){
			int duree = film.getDuree();
			int heure = duree /60;
			int minutes = duree%60;
			dureeFormatee =  heure + " h " + minutes;
		}
		return dureeFormatee;	
	}

	public int getTotalPage(PageRequest pageRequest) {
		return dao.getTotalPage(pageRequest);
	}

	public List<Film> getFilmByRealisateur(PageRequest pageRequest, Integer idRealisateur) {
		List<Film> films = dao.getFilmByRealisateur(pageRequest, idRealisateur);	
		return films;
	}
}

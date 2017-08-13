package com.skalvasociety.skalva.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.dao.IFilmDao;

@Service("filmService")
@Transactional
public class FilmService extends AbstractService<Integer, Film> implements IFilmService {
	
	@SuppressWarnings("unused")
	@Autowired
	private IFilmDao dao;

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

	public List<Realisateur> getRealisateurByListeFilm(List<Film> listeFilm) {
		List<Realisateur> listeRealisateur = new LinkedList<Realisateur>();
		for (Film film : listeFilm) {
			for (Realisateur realisateur : film.getRealisateurs()) {
				if(!listeRealisateur.contains(realisateur)){
					listeRealisateur.add(realisateur);
				}
			}
		}
		return listeRealisateur;
	}
	
	public List<Genre> getGenreByListeFilm(List<Film> listeFilm) {
		List<Genre> listeGenre = new LinkedList<Genre>();
		for (Film film : listeFilm) {
			for (Genre genre : film.getGenres()) {
				if(!listeGenre.contains(genre)){
					listeGenre.add(genre);
				}
			}
		}
		return listeGenre;
	}
	
	public List<Pays> getPaysByListeFilm(List<Film> listeFilm) {
		List<Pays> listePays = new LinkedList<Pays>();
		for (Film film : listeFilm) {
			for (Pays pays : film.getPays()) {
				if(!listePays.contains(pays)){
					listePays.add(pays);
				}
			}
		}
		return listePays;
	}
}

package com.skalvasociety.skalva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.dao.IFilmPersonnageDao;
import com.skalvasociety.skalva.tmdbObject.Cast;

@Service("filmPersonnageService")
@Transactional
public class FilmPersonnageService implements IFilmPersonnageService {
	
	@Autowired
	private IFilmPersonnageDao filmPersonnageDao; 
	
	@Autowired
    private IActeurService acteurService;	


	public void saveFilmPersonnage(FilmPersonnage filmPersonnage) {
		filmPersonnageDao.saveFilmPersonnage(filmPersonnage);
	}

	public FilmPersonnage getFilmPersonnagebyFilmActeur(Film film, Acteur acteur) {		
		return filmPersonnageDao.getFilmPersonnagebyFilmActeur(film, acteur);
	}

	public FilmPersonnage castToFilmPersonnage(Cast cast, Film film) {
		if (cast == null){
			return null;
		}
		Acteur acteur = acteurService.getActeurIdTMDB(cast.getId());
		if (acteur == null){
			acteur = new Acteur();
			acteur.setIdTMDB(cast.getId());
			acteur.setNom(cast.getName());
			acteur.setPhoto(cast.getProfile_path());
			acteurService.saveActeur(acteur);
		}
		FilmPersonnage personnage = getFilmPersonnagebyFilmActeur(film, acteur);
		if (personnage == null){
			personnage = new FilmPersonnage();
			personnage.setActeur(acteur);
			personnage.setFilm(film);
			personnage.setNom(cast.getCharacter());
			saveFilmPersonnage(personnage);
		}				
		return personnage;
	}

}

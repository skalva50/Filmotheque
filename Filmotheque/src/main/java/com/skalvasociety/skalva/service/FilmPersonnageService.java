package com.skalvasociety.skalva.service;

import java.io.Serializable;

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
public class FilmPersonnageService extends AbstractService<Serializable, FilmPersonnage> implements IFilmPersonnageService {
	
	@Autowired
	private IFilmPersonnageDao filmPersonnageDao; 
	
	@Autowired
    private IActeurService acteurService;

	public FilmPersonnage getFilmPersonnagebyFilmActeur(Film film, Acteur acteur) {		
		return filmPersonnageDao.getFilmPersonnagebyFilmActeur(film, acteur);
	}

	public FilmPersonnage castToFilmPersonnage(Cast cast, Film film) {
		if (cast == null){
			return null;
		}
		Acteur acteur = acteurService.getActeurIdTMDB(cast.getId());
		if (acteur == null){
			acteur = acteurService.castToActeur(cast);
		}
		FilmPersonnage personnage = getFilmPersonnagebyFilmActeur(film, acteur);
		if (personnage == null){
			personnage = new FilmPersonnage();
			personnage.setActeur(acteur);
			personnage.setFilm(film);
			personnage.setNom(cast.getCharacter());
			save(personnage);
		}				
		return personnage;
	}

}

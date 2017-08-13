package com.skalvasociety.skalva.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.dao.ISeriePersonnageDao;
import com.skalvasociety.skalva.tmdbObject.Cast;

@Service("seriePersonnageService")
@Transactional
public class SeriePersonnageService extends AbstractService<Serializable, SeriePersonnage> implements ISeriePersonnageService {

	@Autowired
	private ISeriePersonnageDao seriePersonnageDao;
	
	@Autowired
    private IActeurService acteurService;
	
	public SeriePersonnage getSeriePersonnagebySerieActeur(Serie serie, Acteur acteur) {
		return seriePersonnageDao.getSeriePersonnagebySerieActeur(serie, acteur);
	}

	public SeriePersonnage castToSeriePersonnage(Cast cast, Serie serie) {
		if (cast == null){
			return null;
		}
		Acteur acteur = acteurService.getActeurIdTMDB(cast.getId());
		if (acteur == null){
			acteur = acteurService.castToActeur(cast);
		}
		SeriePersonnage personnage = getSeriePersonnagebySerieActeur(serie, acteur);
		if (personnage == null){
			personnage = new SeriePersonnage();
			personnage.setActeur(acteur);
			personnage.setSerie(serie);
			personnage.setNom(cast.getCharacter());
			save(personnage);
		}				
		return personnage;
	}

}

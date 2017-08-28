package com.skalvasociety.skalva.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.dao.IActeurDao;
import com.skalvasociety.skalva.tmdbObject.Cast;

@Service("acteurService")
@Transactional
public class ActeurService extends AbstractService<Serializable, Acteur> implements IActeurService {
	
	@Autowired
	IActeurDao acteurDao;
	
	@Autowired
	IPersonneService personneService;

	public Acteur getActeurIdTMDB(Integer idTmdb) {		
		return acteurDao.getActeurIdTMDB(idTmdb);
	}

	public Acteur castToActeur(Cast cast) {
		Acteur acteur = new Acteur();
		acteur.setIdTMDB(cast.getId());
		acteur.setNom(cast.getName());
		acteur.setPhoto(cast.getProfile_path());
		personneService.completeProfile(acteur);
		save(acteur);
		return acteur;
	}
	
	/**
	 * Supprime les acteurs sans film et series
	 */
	public void deleteActeurObsolete(){
		List<Acteur> acteurs = this.getAll();
		for (Acteur acteur : acteurs) {
			
			if((acteur.getFilmPersonnages()== null || acteur.getFilmPersonnages().isEmpty()) &&
				(acteur.getSeriePersonnages() == null || acteur.getSeriePersonnages().isEmpty()))
			{
				this.delete(acteur);
			}
		}

	}
}

package com.skalvasociety.skalva.service;

import java.io.Serializable;

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
}

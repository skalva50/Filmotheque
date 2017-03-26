package com.skalvasociety.skalva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.dao.IRealisateurDao;
import com.skalvasociety.skalva.tmdbObject.Crew;

@Service("realisateurService")
@Transactional
public class RealisateurService implements IRealisateurService {
	
	@Autowired
    private IRealisateurDao realisateurDao;	

	public Realisateur crewToRealisateur(Crew crew) {
		if (crew == null || !crew.getJob().equals("Director"))
			return null;
		Realisateur realisateur = realisateurDao.getRealisateurByIdTMDB(crew.getId());
		if(realisateur == null){
			realisateur = new Realisateur();
			realisateur.setIdTMDB(crew.getId());
			realisateur.setNom(crew.getName());
			realisateur.setPhoto(crew.getProfile_path());
			realisateurDao.saveRealisateur(realisateur);
		}	
		return realisateur;
	}

}

package com.skalvasociety.skalva.service;

import java.util.List;

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
	
	@Autowired
	IPersonneService personneService;

	public Realisateur crewToRealisateur(Crew crew) {
		if (crew == null || !crew.getJob().equals("Director"))
			return null;
		Realisateur realisateur = realisateurDao.getRealisateurByIdTMDB(crew.getId());
		if(realisateur == null){
			realisateur = new Realisateur();
			realisateur.setIdTMDB(crew.getId());
			realisateur.setNom(crew.getName());
			realisateur.setPhoto(crew.getProfile_path());
			personneService.completeProfile(realisateur);
			realisateurDao.saveRealisateur(realisateur);
		}	
		return realisateur;
	}

	public Realisateur getRealisateurById(Integer id) {		
		return realisateurDao.getRealisateurById(id);
	}
	
	public List<Realisateur> getAll(){
		return realisateurDao.getAll();
	}

}

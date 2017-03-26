package com.skalvasociety.skalva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.dao.IActeurDao;

@Service("acteurService")
@Transactional
public class ActeurService implements IActeurService {
	
	@Autowired
	IActeurDao acteurDao;
	
	public void saveActeur(Acteur acteur) {
		acteurDao.saveActeur(acteur);

	}

	public Acteur getActeurIdTMDB(Integer idTmdb) {		
		return acteurDao.getActeurIdTMDB(idTmdb);
	}

}

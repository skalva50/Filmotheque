package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.dao.ISaisonDao;

@Service("saisonService")
@Transactional
public class SaisonService implements ISaisonService {

	@Autowired
	ISaisonDao saisonDao;
	
	public void saveSaison(Saison saison) {
		saisonDao.saveSaison(saison);
	}

	public List<Saison> getSaisonByIdSerie(Serie serie) {
		return saisonDao.getSaisonByIdSerie(serie);
	}

}

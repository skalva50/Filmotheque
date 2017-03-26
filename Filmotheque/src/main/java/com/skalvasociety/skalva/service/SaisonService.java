package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.dao.ISaisonDao;
import com.skalvasociety.skalva.tmdbObject.SerieSaisonDetails;

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

	public Saison getSaisonById(Integer idSaison) {		
		return saisonDao.getSaisonById(idSaison);
	}
	
	

	public void serieSaisonDetailstoSaison(SerieSaisonDetails serieSaisonDetails, Saison saison) {
		saison.setAffiche(serieSaisonDetails.getPoster_path());
		saison.setResume(serieSaisonDetails.getOverview());
		saison.setNumero(serieSaisonDetails.getSeason_number());	
		saison.setDateSortie(serieSaisonDetails.getAir_date());				
	}

	public Saison getSaisonByIdSerieNumSaison(Serie serie, Integer numSaison) {		
		return saisonDao.getSaisonByIdSerieNumSaison(serie, numSaison);
	}

}

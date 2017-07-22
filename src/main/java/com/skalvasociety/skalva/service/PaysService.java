package com.skalvasociety.skalva.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.dao.IPaysDao;
import com.skalvasociety.skalva.tmdbObject.Country;

@Service("paysService")
@Transactional
public class PaysService extends AbstractService<Serializable, Pays> implements IPaysService {
	
	@Autowired
	IPaysDao paysDao;

	public Pays getPaysbyIdIso(String idIso) {		
		return paysDao.getPaysbyIdIso(idIso);
	}


	public Pays countryToPays(Country country) {
		Pays pays = new Pays();
		pays.setIdIso(country.getIso_3166_1());
		pays.setNom(country.getName());
		return pays;
	}

	public Pays countrySerieToPays(String country) {
		Pays pays = new Pays();
		pays.setIdIso(country);
		return pays;
	}

	public void majCountrytoPays(Country country, Pays pays) {
		pays.setNom(country.getName());
		
	}
}

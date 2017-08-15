package com.skalvasociety.skalva.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skalvasociety.skalva.bean.Personne;
import com.skalvasociety.skalva.tmdbObject.People;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;
import com.skalvasociety.skalva.tools.Convert;

@Service("personneService")
@Transactional
public class PersonneService implements IPersonneService {

	@Autowired
    private Environment environment;
	
	public void completeProfile(Personne personne) {			
		String API_KEY = environment.getProperty("tmdb.API_KEY");
		TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);	
		try {
			People people = tmdbRequest.getPeopleDetail(personne.getIdTMDB());
			if(people != null){
				personne.setBiographie(people.getBiography());
				personne.setDateDeces(new Convert().stringToDate(people.getDeathday()));
				personne.setDateNaissance(new Convert().stringToDate(people.getBirthday()));
				personne.setLieuNaissance(people.getPlace_of_birth());
				personne.setPopularite(people.getPopularity());
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}	

	}

}

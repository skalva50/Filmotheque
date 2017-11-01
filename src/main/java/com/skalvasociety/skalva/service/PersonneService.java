package com.skalvasociety.skalva.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skalvasociety.skalva.bean.Personne;
import com.skalvasociety.skalva.bean.Sexe;
import com.skalvasociety.skalva.tmdbObject.People;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;
import com.skalvasociety.skalva.tools.Convert;

@Service("personneService")
@Transactional
public class PersonneService implements IPersonneService {
	private Logger logger = Logger.getLogger(PersonneService.class);
	@Autowired
    private Environment environment;
	
	public void completeProfile(Personne personne) {			
		String API_KEY = environment.getProperty("tmdb.API_KEY");
		TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);	
		try {
			People people = tmdbRequest.getPeopleDetail(personne.getIdTMDB());
			if(people != null){
				personne.setBiographie(people.getBiography());
				if(people.getGender() == 1 || people.getGender() == 2){
					Sexe sexe = new Sexe();
					sexe.setId(people.getGender());
					personne.setSexe(sexe);
				}				
				personne.setDateDeces(new Convert().stringToDate(people.getDeathday()));
				personne.setDateNaissance(new Convert().stringToDate(people.getBirthday()));
				personne.setLieuNaissance(people.getPlace_of_birth());
				if(people.getPopularity() != null){
					personne.setPopularite(people.getPopularity());
				}else{
					personne.setPopularite(0d);
				}
				
			}
		} catch (IOException e) {			
			logger.error(e.getMessage(), e.getCause());
		}	

	}

}

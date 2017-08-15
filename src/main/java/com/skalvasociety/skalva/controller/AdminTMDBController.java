package com.skalvasociety.skalva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.service.IFilmService;

@Controller
public class AdminTMDBController {
	
	@Autowired
	IFilmService filmService;
	
	@RequestMapping(value = { "/majTMDB" }, method = RequestMethod.GET)
    public String accueil(
    		@RequestParam(value="idTMDB") Integer idTMDB,
    		@RequestParam(value="idFilm") Integer idFilm
			) {      
		filmService.majFilmByIdTMDB(idFilm, idTMDB);		
        return "redirect:/filmDetails?idFilm="+idFilm;
    }

}

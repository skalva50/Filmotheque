package com.skalvasociety.skalva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.service.IFilmService;
import com.skalvasociety.skalva.service.ISerieService;

@Controller
public class AdminTMDBController {
	
	@Autowired
	IFilmService filmService;
	
	@Autowired
	ISerieService serieService;
	
	@RequestMapping(value = { "/majTMDB" }, method = RequestMethod.GET)
    public String accueil(
    		@RequestParam(value="idTMDB") Integer idTMDB,
    		@RequestParam(value="idFilm", required = false) Integer idFilm,
    		@RequestParam(value="idSerie", required = false) Integer idSerie
			) {
		String result = "";
		if(idFilm != null && idFilm != 0){		
			filmService.majFilmByIdTMDB(idFilm, idTMDB);		
			result = "redirect:/filmDetails?idFilm="+idFilm;
		}
		if(idSerie != null && idSerie != 0){		
			serieService.majSerieByIdTMDB(idSerie, idTMDB);		
			result = "redirect:/saisons?idSerie="+idSerie;
		}
		
		return result;
    }

}

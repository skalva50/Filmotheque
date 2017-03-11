package com.skalvasociety.skalva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.service.IFilmService;

@Controller
public class FilmController {
	
	@Autowired
	IFilmService service;
	
    @RequestMapping(value = {"/films" }, method = RequestMethod.GET)
    public String listFilm(ModelMap model) { 
        List<Film> films = service.findAllFilms();
        model.addAttribute("films", films);
        model.addAttribute("nbFilms", films.size());
        return "films";
    }
    
	@RequestMapping(value="/filmDetails" ,method = RequestMethod.GET)
	public String filmById(@RequestParam(value="idFilm") Integer idFilm, ModelMap model){	
		if (idFilm == null)
			return "redirect:/films";
		Film film = service.getFilmById(idFilm);
		if(film != null){
			model.addAttribute("film", film);
			return "filmDetails";
		}else{
			return "redirect:/films";
		}
		
	}
}

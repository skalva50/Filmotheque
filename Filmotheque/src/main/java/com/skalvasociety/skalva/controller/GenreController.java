package com.skalvasociety.skalva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skalvasociety.skalva.service.IGenreService;

@Controller
public class GenreController {
	
	@Autowired
	IGenreService service;
	
	@RequestMapping(value = { "/majGenre" }, method = RequestMethod.GET)
	public String majGenre(){
		service.majGenre();
		return "redirect:/series";
	}

}

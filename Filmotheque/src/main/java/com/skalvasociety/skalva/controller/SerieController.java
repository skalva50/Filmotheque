package com.skalvasociety.skalva.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.service.ISerieService;

@Controller
public class SerieController {
	
	@Autowired
	ISerieService service;
	
    @RequestMapping(value = { "/series" }, method = RequestMethod.GET)
    public String listSerie(ModelMap model) { 
        List<Serie> series = service.getAllSeries();
        model.addAttribute("series", series);
        return "series";
    }
	
	@RequestMapping(value = { "/majSerie" }, method = RequestMethod.GET)
    public String majFichiers() {
    	service.majSerie();
        return "redirect:/series";
        
    }
}

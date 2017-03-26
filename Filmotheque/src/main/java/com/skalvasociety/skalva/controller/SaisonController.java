package com.skalvasociety.skalva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.service.ISaisonService;

@Controller
public class SaisonController {
	
	@Autowired
	ISaisonService service;
	
	@RequestMapping(value="/episodes" ,method = RequestMethod.GET)
	public String episodesById(@RequestParam(value="idSaison") Integer idSaison, ModelMap model){	
		if (idSaison == null)
			return "redirect:/series";
		Saison saison = service.getSaisonById(idSaison);
		if(saison != null){
			model.addAttribute("saison", saison);
			return "episodes";
		}else{
			return "redirect:/series";
		}		
	}
}

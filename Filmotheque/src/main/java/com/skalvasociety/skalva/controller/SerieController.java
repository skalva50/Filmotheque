package com.skalvasociety.skalva.controller;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.service.ISerieService;

@Controller
@Transactional
public class SerieController {
	
	@Autowired
	ISerieService service;
	
    @RequestMapping(value = { "/series" }, method = RequestMethod.GET)
    public String listSerie(ModelMap model) { 
        List<Serie> series = service.getAllSeries();        
        model.addAttribute("series", series);
        model.addAttribute("nbSeries", series.size());
        return "series";
    }
    
    @RequestMapping(value="/saisons" ,method = RequestMethod.GET)
	public String serieById(@RequestParam(value="idSerie") Integer idSerie, ModelMap model){	
		if (idSerie == null)
			return "redirect:/series";
		Serie serie = service.getSerieById(idSerie);
		if(serie != null){
			model.addAttribute("serie", serie);
			
			List<SeriePersonnage> personnages = serie.getPersonnages();
			for (SeriePersonnage seriePersonnage : personnages) {
				seriePersonnage.getActeur().getNom();
				seriePersonnage.getActeur().getPhoto();
			}
			model.addAttribute("personnages", personnages);
			
			List<Realisateur> realisateurs = serie.getRealisateurs(); 
			for (Realisateur realisateur : realisateurs) {
				realisateur.getId();
				realisateur.getPhoto();
				realisateur.getNom();
			}
			model.addAttribute("realisateurs", realisateurs);
			
			return "saisons";
		}else{
			return "redirect:/series";
		}		
	}    
    
	
	@RequestMapping(value = { "/majSerie" }, method = RequestMethod.GET)
    public String majFichiers() {
    	service.majSerie();
        return "redirect:/series";
        
    }
}

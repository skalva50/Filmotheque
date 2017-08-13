package com.skalvasociety.skalva.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skalvasociety.skalva.service.IRealisateurService;

@Controller
@Transactional
//Permet de garder les choix de filtre et de titre de l'utilisateur durant toute sa session
@SessionAttributes( value="realisateursModel", types={RealisateurViewModel.class})
public class RealisateursController {
	@Autowired
	IRealisateurService realisateurService;		
	
    @ModelAttribute("realisateursModel")   
    public RealisateurViewModel addRealisateurModelToSessionScope() {
    	RealisateurViewModel realisateursModel = new RealisateurViewModel(realisateurService);     	
    	return realisateursModel;
    }
	
    @RequestMapping(value = {"/realisateurs" }, method = RequestMethod.GET)
    public String listActeurs(
    		@RequestParam(value="numPage",required = false ) Integer numPage ,
    		//@RequestParam(value="clearFiltre",required = false ) FilmFilterBy clearFiltre ,
    		@ModelAttribute("realisateursModel") RealisateurViewModel realisateursModel		
    		) {
    	
    	if(numPage != null && numPage !=0){
    		realisateursModel.setCurrentPage(numPage);
    	} 
    	realisateursModel.refreshModel();   	    	
        return "realisateurs";
    }

}

package com.skalvasociety.skalva.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skalvasociety.skalva.enumeration.ActeurFilterBy;
import com.skalvasociety.skalva.service.IActeurService;
import com.skalvasociety.skalva.service.ISexeService;


@Controller
@Transactional
//Permet de garder les choix de filtre et de titre de l'utilisateur durant toute sa session
@SessionAttributes( value="acteursModel", types={ActeurViewModel.class})
public class ActeursController {
	
	@Autowired
	IActeurService acteurService;	
	
	@Autowired
	ISexeService sexeService;	
	
    @ModelAttribute("acteursModel")   
    public ActeurViewModel addActeurModelToSessionScope() {
    	ActeurViewModel acteurModel = new ActeurViewModel(acteurService,sexeService);     	
    	return acteurModel;
    }
	
    @RequestMapping(value = {"/acteurs" }, method = RequestMethod.GET)
    public String listActeurs(
    		@RequestParam(value="numPage",required = false ) Integer numPage ,
    		@RequestParam(value="clearFiltre",required = false ) ActeurFilterBy clearFiltre ,
    		@ModelAttribute("acteursModel") ActeurViewModel acteurModel		
    		) {
    	
    	if(numPage != null && numPage !=0){
    		acteurModel.setCurrentPage(numPage);
    	} 
    	acteurModel.setClearFiltre(clearFiltre);
    	acteurModel.refreshModel();   	    	
        return "acteurs";
    }

}

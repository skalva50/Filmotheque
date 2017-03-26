package com.skalvasociety.skalva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skalvasociety.skalva.bean.Fichier;
import com.skalvasociety.skalva.service.IFichierService;

@Controller
public class FichierController {
	
    @Autowired
    IFichierService service;
    
    @RequestMapping(value = { "/listFichiers" }, method = RequestMethod.GET)
    public String listFichiers(ModelMap model) { 
        List<Fichier> fichiers = service.findAllFichiers();
        model.addAttribute("fichiers", fichiers);
        return "allfichiers";
    }
    
    
    @RequestMapping(value = { "/maj" }, method = RequestMethod.GET)
    public String majFichiers() {
    	service.majFichier();
        return "redirect:/films";
    }

}

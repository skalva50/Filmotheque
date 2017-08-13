package com.skalvasociety.skalva.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AccueilController {
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String accueil(ModelMap model) {       

        return "accueil";
    }
	
	
	@RequestMapping(value = { "/administration" }, method = RequestMethod.GET)
    public String administration(ModelMap model) {       

        return "administration";
    }
	
	@RequestMapping(value = { "/apropos" }, method = RequestMethod.GET)
    public String apropos(ModelMap model) {       

        return "apropos";
    }
	
}

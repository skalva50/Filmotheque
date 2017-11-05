package com.skalvasociety.skalva.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.FilmOrderBy;
import com.skalvasociety.skalva.enumeration.SerieOrderBy;
import com.skalvasociety.skalva.enumeration.SortDirection;
import com.skalvasociety.skalva.service.IFilmService;
import com.skalvasociety.skalva.service.ISerieService;
import com.skalvasociety.skalva.service.IUserProfileService;
import com.skalvasociety.skalva.service.IUserService;

@Transactional
@Controller
public class AccueilController {
	@Autowired 
	IUserService userService;
	
	@Autowired 
	IFilmService filmService;
	
	@Autowired 
	ISerieService serieService;
	
	@Autowired 
	IUserProfileService userProfileService;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String accueil(ModelMap model){	
		model.addAttribute("user", getPrincipal());
		
		PageRequest<Film> pageRequestFilm = new PageRequest<Film>(1, 6, SortDirection.DESC, FilmOrderBy.dateAjout); 
		List<Film> films = filmService.getAllByPage(pageRequestFilm);
		for (Film film : films) {
			List<Genre> list = film.getGenres();
			for (Genre genre : list) {
				genre.getLibelle();
			}
		}
		model.addAttribute("films", films);
		
		PageRequest<Serie> pageRequestSerie = new PageRequest<Serie>(1, 6, SortDirection.DESC, SerieOrderBy.dateAjout); 
		List<Serie> series = serieService.getAllByPage(pageRequestSerie);
		for (Serie serie : series) {
			List<Genre> list = serie.getGenres();
			for (Genre genre : list) {
				genre.getLibelle();
			}
		}
		model.addAttribute("series", series);
		
        return "accueil";
    }	
	
    @Value("${build.version}")
    private String buildVersion;

	@RequestMapping(value = { "/apropos" }, method = RequestMethod.GET)
    public String apropos(ModelMap model) {    
		model.addAttribute("version", buildVersion);
        return "apropos";
    }
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value="/fichierInexistant", method = RequestMethod.GET)
    public String fichierInexistant (ModelMap model, @RequestParam(value="fichier") String fichier) {
    	model.addAttribute("Fichier", fichier);
        return "fichierInexistant";
    }
    
    @RequestMapping(value = "/robots.txt", produces = {"text/plain"}, method = RequestMethod.GET)
    @ResponseBody
    public String getRobotsTxt() {
        return "User-agent: *" + 
       "\n" + "Disallow: /"; 
       
    }
    
    
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
}

package com.skalvasociety.skalva.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.MediaTMDB;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.enumeration.FilmFilterBy;
import com.skalvasociety.skalva.service.IFichierService;
import com.skalvasociety.skalva.service.IFilmService;
import com.skalvasociety.skalva.tmdbObject.ResultsSearchMovie;
import com.skalvasociety.skalva.tmdbObject.SearchMovie;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;

@Controller
@Transactional
//Permet de garder les choix de filtre et de titre de l'utilisateur durant toute sa session
@SessionAttributes( value="filmModel", types={FilmViewModel.class})
public class FilmController {
	
	@Autowired
	IFilmService filmService;
	
	@Autowired
	IFichierService fichierService;
	
	@Autowired
    private Environment environment;	
	
	
    @ModelAttribute("filmModel")   
    public FilmViewModel addFilmModelToSessionScope() {
    	FilmViewModel filmModel = new FilmViewModel(filmService);     	
    	return filmModel;
    }
	
    @RequestMapping(value = {"/films" }, method = RequestMethod.GET)
    public String listFilm(
    		@RequestParam(value="numPage",required = false ) Integer numPage ,
    		@RequestParam(value="clearFiltre",required = false ) FilmFilterBy clearFiltre ,    		
    		@ModelAttribute("filmModel") FilmViewModel filmModel		
    		) {
    	
    	if(numPage != null && numPage !=0){
    		filmModel.setCurrentPage(numPage);
    	}
    	filmModel.setClearFiltre(clearFiltre);
    	filmModel.refreshModel();   	    	
        return "films";
    }    

	@RequestMapping(value="/films/filmDetails" ,method = RequestMethod.GET)
	public String filmById(@RequestParam(value="idFilm") Integer idFilm, ModelMap model){	
		if (idFilm == null)
			return "redirect:/films";
		Film film = filmService.getByKey(idFilm);
		if(film != null){
			model.addAttribute("film", film);
			model.addAttribute("dureeFormatee", filmService.getDureeFormatee(film));			
			
			List<Pays> listPays = film.getPays();
			for (Pays pays : listPays) {
				pays.getNom();
			}
			model.addAttribute("pays" , listPays);
			
			List<Video> listVideos = film.getVideos();
			for (Video video : listVideos) {
				video.getNom();
				video.getSiteVideo();
				video.getCleVideo();
			}
			model.addAttribute("videos", listVideos);
			
			List<FilmPersonnage> listPersonnage = film.getPersonnages();
			for (FilmPersonnage filmPersonnage : listPersonnage) {
				filmPersonnage.getActeur().getNom();
				filmPersonnage.getActeur().getPhoto();
				filmPersonnage.getActeur().getIdTMDB();
			}			
			model.addAttribute("personnages", listPersonnage);
			
			List<Realisateur> listRealisateurs = film.getRealisateurs();
			for (Realisateur realisateur : listRealisateurs) {
				realisateur.getId();
				realisateur.getNom();
				realisateur.getPhoto();
			}		
			model.addAttribute("realisateurs",listRealisateurs );
			return "filmDetails";
		}else{
			return "redirect:/films";
		}		
	}
	
    
    
    @RequestMapping(value = { "/administration/majFilm" }, method = RequestMethod.GET)
    public String majFichiers(ModelMap model) {    	
    	List<MediaTMDB> listAjout = fichierService.majFichier();    	
    	model.addAttribute("listAjout", listAjout);
        return "administration";
    }
	
	@RequestMapping(value="/administration/filmDetailsMaj" ,method = RequestMethod.GET)
	public String majFilm(
			@RequestParam(value="idFilm") Integer idFilm,
			ModelMap model){
		Film film = filmService.getByKey(idFilm);
		if(film != null){			
			model.addAttribute("film", film);
			String API_KEY = environment.getProperty("tmdb.API_KEY");
			TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);
			try {
				SearchMovie movie = tmdbRequest.searchMovie(film.getFichier().getChemin());
				if(movie != null){
					List<ResultsSearchMovie> listMovie = movie.getResults();
					model.addAttribute("listMovie", listMovie);			
				}				
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}	
		return "adminTMDB";
	}
	
	@RequestMapping(value="/administration/deleteFilm" ,method = RequestMethod.GET)
	public String majFilm(ModelMap model){
		List<MediaTMDB> listDelete = filmService.deleteFilmObsolete();
		model.addAttribute("listDelete", listDelete);
        return "administration";
	}	
}
		    		

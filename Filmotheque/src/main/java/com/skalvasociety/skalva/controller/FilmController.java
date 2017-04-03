package com.skalvasociety.skalva.controller;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.Sort;
import com.skalvasociety.skalva.enumeration.SortBy;
import com.skalvasociety.skalva.service.IFilmService;
import com.skalvasociety.skalva.service.IGenreService;
import com.skalvasociety.skalva.service.IRealisateurService;


@Controller
@Transactional
@SessionAttributes( value="filtreFilmModel", types={FiltreFilmModel.class} )
public class FilmController {
	
	@Autowired
	IFilmService filmService;	
	
	@Autowired
	IGenreService genreService;
	
	@Autowired
	IRealisateurService realisateurService;
	
	private static final int PAGE_SIZE = 18;
	
    @ModelAttribute("filtreFilmModel")
    public FiltreFilmModel addfiltreFilmModelToSessionScope() {
    	FiltreFilmModel filtreFilmModel = new FiltreFilmModel();
    	filtreFilmModel.setCurrentPage(1);
    	filtreFilmModel.setSortBy(SortBy.note);    	
    	filtreFilmModel.setRealisateurs(realisateurService.getAll());
    	return filtreFilmModel;
    }
	
    @RequestMapping(value = {"/films" }, method = RequestMethod.GET)
    public String listFilm(
    		@RequestParam(value="numPage",required = false ) Integer numPage ,
    		@ModelAttribute("filtreFilmModel") FiltreFilmModel filtreFilmModel,   		
    		ModelMap model) {  
    	
    	if (numPage != null && numPage != 0){
    		filtreFilmModel.setCurrentPage(numPage); 
    	}    	
    	updateModel(filtreFilmModel, model);      
        return "films";
    }      
    
    @RequestMapping(value = {"/filtreFilm" }, method = RequestMethod.POST)
    public String filmsOrder(@ModelAttribute("filtreFilmModel") FiltreFilmModel filtreFilmModel,
    		ModelMap model){   	
    	return "redirect:/films";
    }
    
	private void updateModel(FiltreFilmModel filtreFilmModel, ModelMap model) {
		PageRequest pageRequest = new PageRequest(filtreFilmModel.getCurrentPage(), PAGE_SIZE, Sort.ASC, filtreFilmModel.getSortBy());
		
		List<Film> films = new LinkedList<Film>();
		if(filtreFilmModel.getIdRealisateur() !=  null && filtreFilmModel.getIdRealisateur() !=  0){
			films = filmService.getFilmByRealisateur(pageRequest, filtreFilmModel.getIdRealisateur());
		}else{
			films = filmService.getFilmsPage(pageRequest);	
		}
        
        model.addAttribute("films", films);
        
        model.addAttribute("nbFilms", films.size());
        
        int totalPage = filmService.getTotalPage(pageRequest);
        model.addAttribute("totalPage", totalPage);
        
        int begin = Math.max(1, filtreFilmModel.getCurrentPage() - 5);
        int end = Math.min(begin + 10, totalPage);
        
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
                     
        model.addAttribute("listTri", SortBy.values());
	} 

	@RequestMapping(value="/filmDetails" ,method = RequestMethod.GET)
	public String filmById(@RequestParam(value="idFilm") Integer idFilm, ModelMap model){	
		if (idFilm == null)
			return "redirect:/films";
		Film film = filmService.getFilmById(idFilm);
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
}
		    		

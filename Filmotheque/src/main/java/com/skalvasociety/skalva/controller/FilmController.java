package com.skalvasociety.skalva.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.service.IFilmService;

@Controller
@Transactional
public class FilmController {
	
	@Autowired
	IFilmService service;
	
    @RequestMapping(value = {"/films" }, method = RequestMethod.GET)
    public String listFilm(ModelMap model) {
        List<Film> films = service.findAllFilms();
        model.addAttribute("films", films);
        model.addAttribute("nbFilms", films.size());
        return "films";
    }   

	@RequestMapping(value="/filmDetails" ,method = RequestMethod.GET)
	public String filmById(@RequestParam(value="idFilm") Integer idFilm, ModelMap model){	
		if (idFilm == null)
			return "redirect:/films";
		Film film = service.getFilmById(idFilm);
		if(film != null){
			model.addAttribute("film", film);
			model.addAttribute("dureeFormatee", service.getDureeFormatee(film));	
			
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

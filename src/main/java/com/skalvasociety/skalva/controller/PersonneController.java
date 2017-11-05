package com.skalvasociety.skalva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.service.IActeurService;
import com.skalvasociety.skalva.service.IRealisateurService;

@Controller
@Transactional
public class PersonneController {
	
	@Autowired
	IActeurService acteurService;
	
	@Autowired
	IRealisateurService realisateurService;
	
	@RequestMapping(value="/acteurs/acteur" ,method = RequestMethod.GET)
	public String acteurById(@RequestParam(value="idActeur") Integer idActeur, ModelMap model){	
		if (idActeur == null)
			return "redirect:/films";
		Acteur acteur = acteurService.getByKey(idActeur);
		
		if(acteur != null){
			model.addAttribute("acteur", acteur);
			
			List<FilmPersonnage> listFilmPersonnage = acteur.getFilmPersonnages();
			for (FilmPersonnage filmPersonnage : listFilmPersonnage) {
				filmPersonnage.getFilm().getAffiche();
				filmPersonnage.getFilm().getTitre();
				filmPersonnage.getFilm().getId();
			}
			model.addAttribute("filmpersonnages", listFilmPersonnage);
			
			List<SeriePersonnage> listSeriePersonnage = acteur.getSeriePersonnages();
			for (SeriePersonnage seriePersonnage : listSeriePersonnage) {
				seriePersonnage.getSerie().getAffiche();
				seriePersonnage.getSerie().getTitre();
				seriePersonnage.getSerie().getId();
			}
			model.addAttribute("seriePersonnages", listSeriePersonnage);
			
			return "acteur";
		}else{
			return "redirect:/films";
		}		
	}
	
	@RequestMapping(value="/realisateurs/realisateur" ,method = RequestMethod.GET)
	public String realisateurById(@RequestParam(value="idRealisateur") Integer idRealisateur, ModelMap model){	
		if (idRealisateur == null)
			return "redirect:/films";
		Realisateur realisateur = realisateurService.getByKey(idRealisateur);
		
		if(realisateur != null){
			model.addAttribute("realisateur", realisateur);
			
			List<Film> listFilms = realisateur.getFilms();
			for (Film film : listFilms) {
				film.getAffiche();
				film.getTitre();
				film.getId();
			}
			model.addAttribute("films", listFilms);
			
			List<Serie> listSeries = realisateur.getSerie();
			for (Serie serie : listSeries) {
				serie.getAffiche();
				serie.getTitre();
				serie.getId();
			}
			model.addAttribute("series", listSeries);
			return "realisateur";
		}else{
			return "redirect:/films";
		}		
	}

}

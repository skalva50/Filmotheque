package com.skalvasociety.skalva.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.service.ISaisonService;

@Controller
@Transactional
public class SaisonController {
	
	@Autowired
	ISaisonService service;
	
	@RequestMapping(value="/episodes" ,method = RequestMethod.GET)
	public String episodesById(@RequestParam(value="idSaison") Integer idSaison, ModelMap model){	
		if (idSaison == null)
			return "redirect:/series";
		Saison saison = service.getByKey(idSaison);
		if(saison != null){
			model.addAttribute("saison", saison);
			saison.getSerie().getTitre();
			model.addAttribute("serie", saison.getSerie());
			
			List<Episode> listEpisodes = saison.getEpisodes();
			for (Episode episode : listEpisodes) {
				episode.getId();
				episode.getAffiche();
				episode.getNumero();
				episode.getTitre();
				episode.getResume();
			}
			model.addAttribute("episodes", listEpisodes);	
			
			List<Video> listVideos = saison.getVideos();
			for (Video video : listVideos) {
				video.getNom();
				video.getSiteVideo();
				video.getCleVideo();
			}
			model.addAttribute("videos", listVideos);			
			
			return "episodes";
		}else{
			return "redirect:/series";
		}		
	}
}

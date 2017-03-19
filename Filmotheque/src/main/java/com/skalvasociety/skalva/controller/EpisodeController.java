package com.skalvasociety.skalva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.service.IEpisodeService;

@Controller
public class EpisodeController {
	
	@Autowired
	IEpisodeService episodeService;
	
	@RequestMapping(value="/episode" ,method = RequestMethod.GET)
	public String episodeById(@RequestParam(value="idEpisode") Integer idEpisode, ModelMap model){	
		if (idEpisode == null)
			return "redirect:/series";
		Episode episode = episodeService.getEpisodeById(idEpisode);
		if(episode != null){
			model.addAttribute("episode", episode);
			return "episode";
		}else{
			return "redirect:/series";
		}		
	}

}

package com.skalvasociety.skalva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.service.IEpisodeService;

@Controller
@Transactional
public class EpisodeController {
	
	@Autowired
	IEpisodeService episodeService;
	
	@RequestMapping(value="/episode" ,method = RequestMethod.GET)
	public String episodeById(@RequestParam(value="idEpisode") Integer idEpisode, ModelMap model){	
		if (idEpisode == null)
			return "redirect:/series";
		Episode episode = episodeService.getByKey(idEpisode);
		
		if(episode != null){
			model.addAttribute("episode", episode);
			model.addAttribute("titre", episode.getSaison().getSerie().getTitre());
			model.addAttribute("numSaison", episode.getSaison().getNumero());
			List<Video> listVideos = episode.getVideos();
			for (Video video : listVideos) {
				video.getNom();
				video.getSiteVideo();
				video.getCleVideo();
			}
			model.addAttribute("videos", listVideos);
			
			return "episode";
		}else{
			return "redirect:/series";
		}		
	}

}

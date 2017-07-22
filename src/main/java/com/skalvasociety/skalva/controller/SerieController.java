package com.skalvasociety.skalva.controller;

import java.util.Collections;
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

import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.bean.comparateur.SaisonComparateur;
import com.skalvasociety.skalva.enumeration.SerieFilterBy;
import com.skalvasociety.skalva.service.ISerieService;

@Controller
@Transactional
//Permet de garder les choix de filtre et de titre de l'utilisateur durant toute sa session
@SessionAttributes( value="serieModel", types={SerieViewModel.class})
public class SerieController {
	
	@Autowired
	ISerieService serieService;	
	
    @ModelAttribute("serieModel")   
    public SerieViewModel addSerieModelToSessionScope() {
    	SerieViewModel serieModel = new SerieViewModel(serieService);     	
    	return serieModel;
    }
	
    @RequestMapping(value = { "/series" }, method = RequestMethod.GET)
    public String listSerie(
    		@RequestParam(value="numPage",required = false ) Integer numPage ,
    		@RequestParam(value="clearFiltre",required = false ) SerieFilterBy clearFiltre ,
    		@ModelAttribute("serieModel") SerieViewModel serieModel
    		) { 
    	if(numPage != null && numPage !=0){
    		serieModel.setCurrentPage(numPage);
    	}   
    	serieModel.setClearFiltre(clearFiltre);
    	serieModel.refreshModel();   	    	
        return "series";
    }
     
    
    @RequestMapping(value="/saisons" ,method = RequestMethod.GET)
	public String serieById(@RequestParam(value="idSerie") Integer idSerie, ModelMap model){	
		if (idSerie == null)
			return "redirect:/series";

		Serie serie = serieService.getByKey(idSerie);
		if(serie != null){
			model.addAttribute("serie", serie);
			
			List<Saison> listSaisons = serie.getSaison();
			for (Saison saison : listSaisons) {
					saison.getAffiche();
					saison.getId();
					saison.getNumero();
					saison.getDateSortie();
					saison.getResume();
			}
			Collections.sort(listSaisons, new SaisonComparateur());
			model.addAttribute("saisons" , listSaisons);
			
			List<Pays> listPays = serie.getPays();
			for (Pays pays : listPays) {
				pays.getNom();
			}
			model.addAttribute("pays" , listPays);
			
			List<Video> listVideos = serie.getVideos();
			for (Video video : listVideos) {
				video.getNom();
				video.getSiteVideo();
				video.getCleVideo();
			}
			model.addAttribute("videos", listVideos);
			
			List<SeriePersonnage> personnages = serie.getPersonnages();
			for (SeriePersonnage seriePersonnage : personnages) {
				seriePersonnage.getActeur().getNom();
				seriePersonnage.getActeur().getPhoto();
			}
			model.addAttribute("personnages", personnages);
			
			List<Realisateur> realisateurs = serie.getRealisateurs(); 
			for (Realisateur realisateur : realisateurs) {
				realisateur.getId();
				realisateur.getPhoto();
				realisateur.getNom();
			}
			model.addAttribute("realisateurs", realisateurs);
			
			return "saisons";
		}else{
			return "redirect:/series";
		}		
	}    
    
	
	@RequestMapping(value = { "/majSerie" }, method = RequestMethod.GET)
    public String majFichiers() {
    	serieService.majSerie();
        return "redirect:/series";
        
    }
}

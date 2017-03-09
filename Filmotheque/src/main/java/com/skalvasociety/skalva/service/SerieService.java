package com.skalvasociety.skalva.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.dao.ISerieDao;
import com.skalvasociety.skalva.tmdbObject.SearchSerie;
import com.skalvasociety.skalva.tmdbObject.SerieDetails;
import com.skalvasociety.skalva.tmdbObject.SerieSaisonDetails;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;
import com.skalvasociety.skalva.tmdbObject.Video;


@Service("serieService")
@Transactional
public class SerieService implements ISerieService{
	
	@Autowired
	ISerieDao serieDao;
	
	@Autowired
	ISaisonService saisonService;

	
	public void saveSerie(Serie serie) {
		serieDao.saveSerie(serie);		
	}

	public List<Serie> getAllSeries() {	
		return serieDao.findAllSeries();
	}
	

	public boolean idTMDBExists(Serie serie) {		
		return serieDao.idTMDBExists(serie);
	}
	
	public Serie getSerieByIdTMDB(int idTMDB){
		return serieDao.getSerieByIdTMDB(idTMDB);
	}
	
	public Serie getSerieById(Integer idSerie) {	
		return serieDao.getSerieById(idSerie);
	}	

	public void majSerie() {		
		String path = "/media/Disque_PI/Serie";
		TMDBRequest tmdbRequest = new TMDBRequest();
		List<String> listeNomSerie = this.listDossier(path);
		for (String nameSerie : listeNomSerie) {			
			try {
				SearchSerie searchSerie = tmdbRequest.searchSerie(nameSerie);
				if(searchSerie != null){
					Serie serie = searchSerie.toSerie();						
					
					if (serie != null){
						if(!idTMDBExists(serie)){
							SerieDetails serieDetail = tmdbRequest.getSerieByID(serie.getIdTMDB());
							if (serieDetail != null)
								serieDetail.toSerie(serie);
							Video video = tmdbRequest.getVideoByID(serie);
							if(video != null)								
								video.toMedia(serie);
							saveSerie(serie);
						}else{
							serie = getSerieByIdTMDB(serie.getIdTMDB());
						}
						List<String> listSaisonDossier = this.listDossier(path+"/"+nameSerie);
						
						for (String saisonDossier : listSaisonDossier) {							
							SerieSaisonDetails serieSaisonDetails = tmdbRequest.getSerieSaison(serie.getIdTMDB(), saisonDossier);
							if (serieSaisonDetails != null){
								Saison saison = serieSaisonDetails.toSaison();
								saison.setSerie(serie);
								saisonService.saveSaison(saison);								
							}							
						}						
					}					
				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Lit les dossiers d'un dossier (ne lit pas dans les sous-dossiers)
	 * @param path
	 * @return Liste des noms de dossier
	 */
	private List<String> listDossier(String path){		
		List<String> listDossier = new LinkedList<String>();
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {				
						listDossier.add(files[i].getName());					
				}
			}
		}   	
		return listDossier;
	}

}

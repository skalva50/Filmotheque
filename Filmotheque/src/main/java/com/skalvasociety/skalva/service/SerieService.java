package com.skalvasociety.skalva.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.dao.ISerieDao;
import com.skalvasociety.skalva.tmdbObject.SearchSerie;
import com.skalvasociety.skalva.tmdbObject.SerieDetails;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;


@Service("serieService")
@Transactional
public class SerieService implements ISerieService{
	
	@Autowired
	ISerieDao dao;
	
	public void saveSerie(Serie serie) {
		dao.saveSerie(serie);		
	}
	


	public List<Serie> getAllSeries() {		
		return dao.findAllSeries();
	}
	

	public boolean idTMDBExists(Serie serie) {		
		return dao.idTMDBExists(serie);
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
					if (serie != null && !idTMDBExists(serie)){
						SerieDetails serieDetail = tmdbRequest.getSerieByID(serie.getIdTMDB());
						serieDetail.toSerie(serie);
						saveSerie(serie);
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

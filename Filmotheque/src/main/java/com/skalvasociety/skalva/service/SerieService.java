package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Fichier;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.dao.IGenreDao;
import com.skalvasociety.skalva.dao.ISerieDao;
import com.skalvasociety.skalva.tmdbObject.EpisodeTMDB;
import com.skalvasociety.skalva.tmdbObject.GenreTmdb;
import com.skalvasociety.skalva.tmdbObject.SearchSerie;
import com.skalvasociety.skalva.tmdbObject.SerieDetails;
import com.skalvasociety.skalva.tmdbObject.SerieSaisonDetails;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;
import com.skalvasociety.skalva.tools.Acces;


@Service("serieService")
@Transactional
public class SerieService implements ISerieService{
	
	@Autowired
	ISerieDao serieDao;
	
	@Autowired
    private IGenreDao genreDao;
	
	@Autowired
	ISaisonService saisonService;
	
	@Autowired
	IFichierService fichierService;
	
	@Autowired
	IEpisodeService episodeService;
	
	@Autowired
    private Environment environment;

	
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
		String path = environment.getProperty("serie.path");
		String API_KEY = environment.getProperty("tmdb.API_KEY");
		TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);
		List<String> listeNomSerie = new Acces().listDossier(path);
		for (String nameSerie : listeNomSerie) {			
			try {
				SearchSerie searchSerie = tmdbRequest.searchSerie(nameSerie);
				if(searchSerie != null){
					Serie serie = searchSerietoSerie(searchSerie);				 									
					if (serie != null){
						if(!idTMDBExists(serie)){
							SerieDetails serieDetail = tmdbRequest.getSerieByID(serie.getIdTMDB());
							if (serieDetail != null){
								serieDetailsToSerie(serieDetail, serie);
							}								
							tmdbRequest.getVideoByID(serie);
							saveSerie(serie);
						}else{
							serie = getSerieByIdTMDB(serie.getIdTMDB());
						}
						List<String> listSaisonDossier = new Acces().listDossier(path+"/"+nameSerie);
						
						for (String saisonDossier : listSaisonDossier) {							
							SerieSaisonDetails serieSaisonDetails = tmdbRequest.getSerieSaison(serie.getIdTMDB(), saisonDossier);
							if (serieSaisonDetails != null){
								Saison saison = new Saison();
								serieSaisonDetailstoSaison(serieSaisonDetails, saison);
								saison.setSerie(serie);
								tmdbRequest.getVideoByID(saison);
								saisonService.saveSaison(saison);								
								List<String> listEpisodes = new Acces().listFichierVideo(path+"/"+nameSerie+"/Saison "+saison.getNumero());
								for (String nomFichier : listEpisodes) {
									Fichier fichier = new Fichier();
									fichier.setChemin(nameSerie+"/Saison "+saison.getNumero()+"/"+nomFichier);
									if(fichierService.isFichierCheminUnique(fichier.getChemin())){
										fichierService.saveFichier(fichier);										
										EpisodeTMDB episodeTMDB = tmdbRequest.getEpisode(serie.getIdTMDB(), saison.getNumero(), nomFichier);
										if (episodeTMDB == null){
											System.out.println("Introuvable sur TMDB: " + nomFichier);
										}else{
											Episode episode = new Episode();
											episodeTmdbToEpisode(episodeTMDB, episode);
											episode.setFichier(fichier);
											episode.setSaison(saison);
											tmdbRequest.getVideoByID(episode);
											episodeService.saveEpisode(episode);
											System.out.println("Episode: " + episode.getTitre());
										}										
									}
								}
							}							
						}						
					}					
				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	private Serie searchSerietoSerie(SearchSerie searchSerie){
		Serie serie = null;
		if (searchSerie !=  null && !searchSerie.getResults().isEmpty()){
			serie = new Serie();
			serie.setIdTMDB(searchSerie.getResults().get(0).getId());			
		}
		return serie ;
	}
	
	private void serieDetailsToSerie(SerieDetails serieDetails, Serie serie){
		serie.setResume(serieDetails.getOverview());
		serie.setAffiche(serieDetails.getPoster_path());
		serie.setTitre(serieDetails.getName());
		serie.setTitreOriginal(serieDetails.getOriginal_name());
		serie.setPopularite(serieDetails.getPopularity());
		serie.setNote(serieDetails.getVote_average());
		serie.setResumeCourt(serie.getResume());
		List<GenreTmdb> listGenreTmdb = serieDetails.getGenres();
		List<Genre> listGenre = serie.getGenres();
		for (GenreTmdb genreTmdb : listGenreTmdb) {
			Genre genre  = genreDao.getGenreByIdTmdb(genreTmdb.getId());
			if(genre == null){
				genre = genreTmdb.toGenre();
				genreDao.saveGenre(genre);
			}				
			listGenre.add(genre);
		}
		serie.setGenres(listGenre);
	}
	
	private void  serieSaisonDetailstoSaison(SerieSaisonDetails serieSaisonDetails, Saison saison){
		saison.setAffiche(serieSaisonDetails.getPoster_path());
		saison.setResume(serieSaisonDetails.getOverview());
		saison.setNumero(serieSaisonDetails.getSeason_number());	
		saison.setDateSortie(serieSaisonDetails.getAir_date());
	}
	
	private void episodeTmdbToEpisode(EpisodeTMDB episodeTMDB, Episode episode){
		episode.setTitre(episodeTMDB.getName());
		episode.setAffiche(episodeTMDB.getStill_path());
		episode.setIdTMDB(episodeTMDB.getId());
		episode.setNumero(episodeTMDB.getEpisode_number());
		episode.setResume(episodeTMDB.getOverview());
	}
}

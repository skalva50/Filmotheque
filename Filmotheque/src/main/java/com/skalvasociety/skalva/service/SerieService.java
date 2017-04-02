package com.skalvasociety.skalva.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Fichier;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.dao.ISerieDao;
import com.skalvasociety.skalva.tmdbObject.Cast;
import com.skalvasociety.skalva.tmdbObject.Crew;
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
    private IGenreService genreService;
	
	@Autowired
    private ISeriePersonnageService personnageService;
	
	@Autowired
	private IRealisateurService realisateurService;
	
	@Autowired
	private ISaisonService saisonService;
	
	@Autowired
	private IFichierService fichierService;
	
	@Autowired
	private IEpisodeService episodeService;
	
	@Autowired
	private IVideoService videoService;
	
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
							saveSerie(serie);
							List<Video> listVideos = tmdbRequest.getVideoByID(serie);
							if(listVideos != null){
								for (Video video : listVideos) {
									videoService.save(video);
								}
							}
								
							List<Cast> listeCasting = tmdbRequest.getCastbyMedia(serie);
							if (listeCasting != null){
								List<SeriePersonnage> listePersonnage = new LinkedList<SeriePersonnage>();
								for (Cast cast : listeCasting) {
									SeriePersonnage personnage = personnageService.castToSeriePersonnage(cast, serie);
									personnage.setSerie(serie);
									listePersonnage.add(personnage);
								}
								serie.setPersonnages(listePersonnage);
							}						
							
							List<Crew> listeCrew = tmdbRequest.getCrewbyMedia(serie);
							if (listeCrew != null){
								List<Realisateur> listeRealisateur = new LinkedList<Realisateur>();
								for (Crew crew : listeCrew) {
									Realisateur realisateur = realisateurService.crewToRealisateur(crew);
									if (realisateur != null){
										listeRealisateur.add(realisateur);									
									}									
								}
								serie.setRealisateurs(listeRealisateur);
							}				
						}else{
							serie = getSerieByIdTMDB(serie.getIdTMDB());
						}
						List<String> listSaisonDossier = new Acces().listDossier(path+"/"+nameSerie);
						
						for (String saisonDossier : listSaisonDossier) {							
							SerieSaisonDetails serieSaisonDetails = tmdbRequest.getSerieSaison(serie.getIdTMDB(), saisonDossier);
							if (serieSaisonDetails != null){								
								Saison saison = saisonService.getSaisonByIdSerieNumSaison(serie, serieSaisonDetails.getSeason_number());
								if (saison == null){
									saison = new Saison();
									saisonService.serieSaisonDetailstoSaison(serieSaisonDetails, saison);
									saison.setSerie(serie);									
									saisonService.saveSaison(saison);
									List<Video> listVideos = tmdbRequest.getVideoByID(saison);
									if(listVideos != null){
										for (Video video : listVideos) {
											videoService.save(video);
										}
									}									
									
									List<Cast> listeCasting = tmdbRequest.getCastbyMedia(saison);
									if (listeCasting != null){
										List<SeriePersonnage> listePersonnage = serie.getPersonnages();
										for (Cast cast : listeCasting) {
											SeriePersonnage personnage = personnageService.castToSeriePersonnage(cast, serie);
											personnage.setSerie(serie);
											listePersonnage.add(personnage);
											System.out.println(personnage.getActeur().getNom());
										}
										serie.setPersonnages(listePersonnage);
									}								
								}																
								List<String> listEpisodes = new Acces().listFichierVideo(path+"/"+nameSerie+"/Saison "+saison.getNumero());
								for (String nomFichier : listEpisodes) {
									Fichier fichier = new Fichier();
									fichier.setChemin(nameSerie+"/Saison "+saison.getNumero()+"/"+nomFichier);
									if(fichierService.isFichierCheminUnique(fichier.getChemin())){																				
										EpisodeTMDB episodeTMDB = tmdbRequest.getEpisode(serie.getIdTMDB(), saison.getNumero(), nomFichier);
										if (episodeTMDB == null){
											System.out.println("Introuvable sur TMDB: " + nomFichier);
										}else{
											Episode episode = episodeService.getEpisodeBySaisonNumEpisode(saison, episodeTMDB.getEpisode_number());
											if (episode == null){
												episode = new Episode();
												fichierService.saveFichier(fichier);
												episodeService.episodeTmdbToEpisode(episodeTMDB, episode);											
												episode.setFichier(fichier);
												episode.setSaison(saison);												
												episodeService.saveEpisode(episode);
												List<Video> listVideos = tmdbRequest.getVideoByID(episode);
												if(listVideos != null){
													for (Video video : listVideos) {
														videoService.save(video);
													}
												}												
												System.out.println("Episode: " + episode.getTitre());
											}											
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
		serie.setDateSortie(serieDetails.getFirst_air_date());
		List<GenreTmdb> listGenreTmdb = serieDetails.getGenres();
		List<Genre> listGenre = serie.getGenres();
		for (GenreTmdb genreTmdb : listGenreTmdb) {
			Genre genre  = genreService.getGenreByIdTmdb(genreTmdb.getId());
			if(genre == null){
				genre = genreTmdb.toGenre();
				genreService.saveGenre(genre);
			}				
			listGenre.add(genre);
		}
		serie.setGenres(listGenre);
	}

}

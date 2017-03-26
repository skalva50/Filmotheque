package com.skalvasociety.skalva.service;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Fichier;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.dao.IFichierDao;
import com.skalvasociety.skalva.tmdbObject.Cast;
import com.skalvasociety.skalva.tmdbObject.Crew;
import com.skalvasociety.skalva.tmdbObject.GenreTmdb;
import com.skalvasociety.skalva.tmdbObject.MovieDetails;
import com.skalvasociety.skalva.tmdbObject.SearchMovie;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;
import com.skalvasociety.skalva.tools.Acces;

@Service("fichierService")
@Transactional
public class FichierService implements IFichierService {
	
	@Autowired
    private IFichierDao dao;
	
	@Autowired
    private IGenreService genreService;
	
	@Autowired
    private IFilmPersonnageService personnageService;
	
	@Autowired
	private IRealisateurService realisateurService;

	@Autowired
	private IFilmService filmService;
	
	@Autowired
    private Environment environment;

	public Fichier findByID(int id) {		
		return dao.findById(id);
	}

	public List<Fichier> findAllFichiers() {
		return dao.findAllFichiers();
	}
	
	public void saveFichier(Fichier fichier) {
			dao.saveFichier(fichier);		
	}
	
	public Fichier findFichierByChemin (String chemin){
		return dao.findFichierByChemin(chemin);
	}
	
	public boolean isFichierCheminUnique(String chemin) {
		Fichier fichier = findFichierByChemin(chemin);		
		if(fichier == null)
			return true;
		else
			return false;
	}
	
	public void majFichier() {		
		String path = environment.getProperty("film.path");
		String API_KEY = environment.getProperty("tmdb.API_KEY");
		TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);		
		List<String> listeFichier = new Acces().listFichierVideo(path);
		for (String chemin : listeFichier) {			
			Fichier fichier = new Fichier();
			fichier.setChemin(chemin);
			if(isFichierCheminUnique(fichier.getChemin())){				
				try {	
					// Recherche de l'idTMDB sur la bas TMDB
					SearchMovie movie = tmdbRequest.searchMovie(fichier.getChemin());
					if (movie != null){
						saveFichier(fichier);
						Film film = movie.toFilm();	
						if (film != null){
							// Creation de l'entree Film avec son idTMDB 
							film.setFichier(fichier);
							filmService.saveFilm(film);
							
							// Mise à jour du detail du film avec son idTMDB
							MovieDetails movieDetails = tmdbRequest.getMovieByID(film.getIdTMDB());
							if(movieDetails !=  null){
								movieDetailsToFilm(movieDetails, film);
							}														
							tmdbRequest.getVideoByID(film);	
							List<Cast> listeCasting = tmdbRequest.getCastbyMedia(film);
							if(listeCasting != null){
								List<FilmPersonnage> listePersonnage = new LinkedList<FilmPersonnage>();							
								for (Cast cast : listeCasting) {
									FilmPersonnage personnage = personnageService.castToFilmPersonnage(cast, film);
									listePersonnage.add(personnage);
								}
								film.setPersonnages(listePersonnage);
							}							
							
							List<Crew> listeCrew = tmdbRequest.getCrewbyMedia(film);
							List<Realisateur> listeRealisateur = new LinkedList<Realisateur>();
							if(listeCrew != null){
								for (Crew crew : listeCrew) {
									Realisateur realisateur = realisateurService.crewToRealisateur(crew);
									if (realisateur != null){
										listeRealisateur.add(realisateur);
										System.out.println(realisateur.getNom());
									}										
								}
								film.setRealisateurs(listeRealisateur);
							}														
						}					
					}																
				} catch (IOException e) {			
					e.printStackTrace();
				}
			}			
		}	
		
	}
	

	private void movieDetailsToFilm(MovieDetails movieDetail, Film film){
		film.setResume(movieDetail.getOverview());
		film.setTitre(movieDetail.getTitle());
		film.setTitreOriginal(movieDetail.getOriginal_title());
		film.setAffiche(movieDetail.getPoster_path());
		film.setPopularite(movieDetail.getPopularity());
		film.setNote(movieDetail.getVote_average());
		film.setResumeCourt(movieDetail.getTagline());
		film.setDateSortie(movieDetail.getRelease_date());
		List<GenreTmdb> listGenreTmdb = movieDetail.getGenres();
		List<Genre> listGenre = film.getGenres();
		for (GenreTmdb genreTmdb : listGenreTmdb) {
			Genre genre  = genreService.getGenreByIdTmdb(genreTmdb.getId());
			if(genre == null){
				genre = genreTmdb.toGenre();
				genreService.saveGenre(genre);
			}
				
			listGenre.add(genre);
		}
		film.setGenres(listGenre);
	}
}

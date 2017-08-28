package com.skalvasociety.skalva.service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.MediaTMDB;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.Video;
import com.skalvasociety.skalva.dao.IFilmDao;
import com.skalvasociety.skalva.tmdbObject.Cast;
import com.skalvasociety.skalva.tmdbObject.Crew;
import com.skalvasociety.skalva.tmdbObject.MovieDetails;
import com.skalvasociety.skalva.tmdbObject.TMDBRequest;

@Service("filmService")
@Transactional
public class FilmService extends AbstractService<Integer, Film> implements IFilmService {
	
	@SuppressWarnings("unused")
	@Autowired
	private IFilmDao dao;
	
	@Autowired
    private Environment environment;
	
	@Autowired
    private IFichierService fichierService;
	
	@Autowired
    private IFilmPersonnageService personnageService;
	
	@Autowired
    private IActeurService acteurService;
	
	@Autowired
	private IRealisateurService realisateurService;
	
	@Autowired
	private IVideoService videoService;

	public String getDureeFormatee(Film film){
		String dureeFormatee = null;
		if(film != null && film.getDuree() != null){
			int duree = film.getDuree();
			int heure = duree /60;
			int minutes = duree%60;
			dureeFormatee =  heure + " h " + minutes;
		}
		return dureeFormatee;	
	}

	public List<Realisateur> getRealisateurByListeFilm(List<Film> listeFilm) {
		List<Realisateur> listeRealisateur = new LinkedList<Realisateur>();
		for (Film film : listeFilm) {
			for (Realisateur realisateur : film.getRealisateurs()) {
				if(!listeRealisateur.contains(realisateur)){
					listeRealisateur.add(realisateur);
				}
			}
		}
		return listeRealisateur;
	}
	
	public List<Genre> getGenreByListeFilm(List<Film> listeFilm) {
		List<Genre> listeGenre = new LinkedList<Genre>();
		for (Film film : listeFilm) {
			for (Genre genre : film.getGenres()) {
				if(!listeGenre.contains(genre)){
					listeGenre.add(genre);
				}
			}
		}
		return listeGenre;
	}
	
	public List<Pays> getPaysByListeFilm(List<Film> listeFilm) {
		List<Pays> listePays = new LinkedList<Pays>();
		for (Film film : listeFilm) {
			for (Pays pays : film.getPays()) {
				if(!listePays.contains(pays)){
					listePays.add(pays);
				}
			}
		}
		return listePays;
	}

	public void majFilmByIdTMDB(Integer idFilm, Integer idTMDB) {
		Film film = getByKey(idFilm);
		film.setIdTMDB(idTMDB);
		
		String API_KEY = environment.getProperty("tmdb.API_KEY");
		TMDBRequest tmdbRequest = new TMDBRequest(API_KEY);		
		MovieDetails movieDetails;
		try {
			movieDetails = tmdbRequest.getMovieByID(film.getIdTMDB());
			if(movieDetails !=  null){
				// Mise à jour des données du film
				fichierService.movieDetailsToFilm(movieDetails, film);
				
				deleteVideos(film);			
				
				List<Video> listVideos = tmdbRequest.getVideoByID(film);
				if(listVideos != null){
					for (Video video : listVideos) {
						videoService.save(video);
					}
				}	
				
				deletePersonnage(film);
				
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
						}										
					}
					film.setRealisateurs(listeRealisateur);
				}	
			}	
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}

	private void deletePersonnage(Film film) {
		List<FilmPersonnage> listePersonnageToDelete = film.getPersonnages();
		if(listePersonnageToDelete != null){
			for (FilmPersonnage filmPersonnage : listePersonnageToDelete) {
				personnageService.delete(filmPersonnage);
			}
		}
	}

	private void deleteVideos(Film film) {
		List<Video> listVideosToDelete = film.getVideos();
		if(listVideosToDelete != null){
			for (Video video : listVideosToDelete) {
				videoService.delete(video);
			}
		}
	}

	public List<MediaTMDB> deleteFilmObsolete() {
		List<MediaTMDB> listDelete = new LinkedList<MediaTMDB>();
		List<Film> films = this.getAll();
		String pathFolder = environment.getProperty("film.path");
		File file = null;
		 
		for (Film film : films) {
			 file = new File(pathFolder+"/"+film.getFichier().getChemin());
			 if(!file.exists()){
				 // suppression des fk associe
				 deletePersonnage(film);
				 deleteVideos(film);
				 // suppression du film
				 listDelete.add(film);
				 this.delete(film);
				 // suppression du fichier
				 fichierService.delete(film.getFichier());
			 }
		}
		// Suppression des acteurs et realisateurs n'ayant plus de lien avec les films ou serie
		acteurService.deleteActeurObsolete();
		realisateurService.deleteRealisateurObsolete();
		return listDelete;
	}
}

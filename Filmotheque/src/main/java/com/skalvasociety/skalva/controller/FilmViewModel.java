package com.skalvasociety.skalva.controller;

import java.util.Collections;
import java.util.List;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FiltreFilm;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.comparateur.GenreComparateur;
import com.skalvasociety.skalva.bean.comparateur.PaysComparateur;
import com.skalvasociety.skalva.bean.comparateur.PersonneComparateur;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.FilmFilterBy;
import com.skalvasociety.skalva.enumeration.FilmOrderBy;
import com.skalvasociety.skalva.service.IFilmService;


public class FilmViewModel {

	private FilmOrderBy filmOrderBy;
	private int currentPage;
	private int totalPage;
	private List<Film> films;
	
	private List<Realisateur> realisateurs;
	private Integer idRealisateur;
	
	private List<Genre> genres;
	private Integer idGenre;
	
	private List<Pays> pays;
	private Integer idPays;
	
	private FilmFilterBy clearFiltre;

	private IFilmService filmService;
	
	private static final int PAGE_SIZE = 18;
	private static final int DEFAULT_PAGE = 1;
	private static final FilmOrderBy DEFAULT_FILM_SORT = FilmOrderBy.note;

	public FilmViewModel(IFilmService filmService){
		this.filmService = filmService;
		initialisation();    	 
	}
	
	private void initialisation(){
		setCurrentPage(DEFAULT_PAGE);
		setFilmOrderBy(DEFAULT_FILM_SORT);
    	
    	// Chargement des resultats par defaut pour l'initialisation
    	PageRequest<Film> pageRequest = new PageRequest<Film>(DEFAULT_PAGE, PAGE_SIZE, DEFAULT_FILM_SORT.getSortDirectionDefaut(), DEFAULT_FILM_SORT);    	
    	this.setFilms( filmService.getAllByFiltrePage(pageRequest, new FiltreFilm()));
    	this.setTotalPage(pageRequest.getTotalPage());
    	
    	//On alimente les filtres avec l'ensemebles des films
    	chargerListeDeroulante(filmService.getAll(DEFAULT_FILM_SORT, DEFAULT_FILM_SORT.getSortDirectionDefaut()));

    
	}
	
	public void refreshModel() {		
		PageRequest<Film> pageRequest = new PageRequest<Film>(this.getCurrentPage(), PAGE_SIZE, this.getFilmOrderBy().getSortDirectionDefaut(), this.getFilmOrderBy());			
		
		checkFiltre();
		
		FiltreFilm filtreFilm = new FiltreFilm();
		if(this.getIdRealisateur() !=  null && this.getIdRealisateur() !=  0){
			filtreFilm.addFiltre(FilmFilterBy.realisateur,this.getIdRealisateur());						
		}
		if(this.getIdGenre() != null && this.getIdGenre() != 0){		
			filtreFilm.addFiltre(FilmFilterBy.genre,this.getIdGenre());
		}
		if(this.getIdPays() != null && this.getIdPays() != 0){		
			filtreFilm.addFiltre(FilmFilterBy.pays,this.getIdPays());
		}
			
		films = filmService.getAllByFiltrePage(pageRequest, filtreFilm);
		this.setFilms(films);
		this.setTotalPage(pageRequest.getTotalPage());  
		
		chargerListeDeroulante(filmService.getByFiltre(filtreFilm));
		
	}
	
	/**
	 * charge les listes deroulantes des filtres en fonction des films chargés dans la liste
	 * @param listeFilms
	 */
	private void chargerListeDeroulante(List<Film> listeFilms){
		//Chargement des listes
		realisateurs = filmService.getRealisateurByListeFilm(listeFilms);
		genres = filmService.getGenreByListeFilm(listeFilms);		
		pays = filmService.getPaysByListeFilm(listeFilms);
		
		// Tri des listes
		Collections.sort(genres, new GenreComparateur());
		Collections.sort(realisateurs, new PersonneComparateur());
		Collections.sort(pays, new PaysComparateur());
	}
 
	private void checkFiltre() {
		if(getClearFiltre() != null){
			switch (getClearFiltre()){
			case pays:
				this.setIdPays(null);
				break;
			case realisateur:
				this.setIdRealisateur(null);
				break;
			case genre:
				this.setIdGenre(null);
				break;
			default:
				break;
			}
		}
	}

	public FilmOrderBy getFilmOrderBy() {
		return filmOrderBy;
	}

	public void setFilmOrderBy(FilmOrderBy filmOrderBy) {
		this.filmOrderBy = filmOrderBy;
	}

	public FilmOrderBy [] getListFilmOrderBy() {
		return FilmOrderBy.values();
	}

	public int getCurrentPage() {
		if (currentPage > getTotalPage())
			return getTotalPage();
		else
			return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public int getBeginPagination() {
		return Math.max(1, getCurrentPage() - 5);
	}

	public int getEndPagination() {
		return Math.min(getBeginPagination() + 10, getTotalPage());
	}

	public List<Genre> getGenres() {
		return genres;
	}
	
	public List<Realisateur> getRealisateurs() {
		return realisateurs;		
	}

	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public Integer getIdRealisateur() {
		return idRealisateur;
	}

	public void setIdRealisateur(Integer idRealisateur) {
		if(idRealisateur != null)
			clearFiltre = null;
		this.idRealisateur = idRealisateur;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Integer idGenre) {
		if(idGenre != null)
			clearFiltre = null;
		this.idGenre = idGenre;
	}

	public List<Pays> getPays() {
		return pays;
	}

	public void setPays(List<Pays> pays) {
		this.pays = pays;
	}

	public Integer getIdPays() {
		return idPays;
	}

	public void setIdPays(Integer idPays) {
		if(idPays != null)
			clearFiltre= null;
		this.idPays = idPays;
	}
	
	public FilmFilterBy getClearFiltre() {
		return clearFiltre;
	}

	public void setClearFiltre(FilmFilterBy clearFiltre) {
		this.clearFiltre = clearFiltre;
	}	
}

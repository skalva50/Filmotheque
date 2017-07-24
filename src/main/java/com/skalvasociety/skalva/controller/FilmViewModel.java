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
import com.skalvasociety.skalva.enumeration.OrderBy;
import com.skalvasociety.skalva.service.IFilmService;


public class FilmViewModel extends AbstractListModel<Film> {
	
	private List<Realisateur> realisateurs;
	private Integer idRealisateur;
	
	private List<Genre> genres;
	private Integer idGenre;
	
	private List<Pays> pays;
	private Integer idPays;
	
	private FilmFilterBy clearFiltre;

	private IFilmService filmService;

	public FilmViewModel(IFilmService service){
		super(service);	
		this.filmService = service;
		setOrderBy(FilmOrderBy.note);
		initialisation(); 	 
	}
	
	@Override
	protected void initialisation() {		
		super.initialisation();
		chargerListeDeroulante(service.getAll(getOrderBy(), getOrderBy().getSortDirectionDefaut()));
	}
	
	public void refreshModel() {		
		PageRequest<Film> pageRequest = new PageRequest<Film>(this.getCurrentPage(), PAGE_SIZE, this.getOrderBy().getSortDirectionDefaut(), this.getOrderBy());			
		
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
			
		liste = service.getAllByFiltrePage(pageRequest, filtreFilm);
		this.setListe(liste);
		this.setTotalPage(pageRequest.getTotalPage());  
		
		chargerListeDeroulante(service.getByFiltre(filtreFilm));
		
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

	@Override
	public FilmOrderBy getOrderBy() {
		return (FilmOrderBy) orderBy;		
	}

	@Override
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = (FilmOrderBy) orderBy;		
	}

	public FilmOrderBy [] getListOrderBy() {
		return FilmOrderBy.values();
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

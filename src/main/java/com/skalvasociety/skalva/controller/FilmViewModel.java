package com.skalvasociety.skalva.controller;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FiltreFilm;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.comparateur.GenreComparateur;
import com.skalvasociety.skalva.bean.comparateur.PaysComparateur;
import com.skalvasociety.skalva.bean.comparateur.PersonneComparateur;
import com.skalvasociety.skalva.enumeration.FilmFilterBy;
import com.skalvasociety.skalva.enumeration.FilmFilterText;
import com.skalvasociety.skalva.enumeration.FilmOrderBy;
import com.skalvasociety.skalva.enumeration.IOrderBy;
import com.skalvasociety.skalva.service.IFilmService;

@Transactional
public class FilmViewModel extends AbstractListFiltreModel<Film, FilmFilterBy> {
	
	private List<Realisateur> realisateurs;
	private Integer idRealisateur;
	
	private List<Genre> genres;
	private Integer idGenre;
	
	private List<Pays> pays;
	private Integer idPays;	
	
	private String titreLike;
	
	private IFilmService filmService;

	public FilmViewModel(IFilmService service){
		super(service);	
		this.filmService = service;
		setOrderBy(FilmOrderBy.note);
		initialisation(); 	 
	}
	
	@Override
	protected void chargerGraphe(){
		for (Film film : liste) {
			List<Genre> list = film.getGenres();
			for (Genre genre : list) {
				genre.getLibelle();
			}
		}
	}
	
	@Override
	IFiltre<Film> chargerCriteresFiltre() {
		FiltreFilm filtre = new FiltreFilm();
		if(this.getIdRealisateur() !=  null && this.getIdRealisateur() !=  0){
			filtre.addFiltre(FilmFilterBy.realisateur,this.getIdRealisateur());						
		}
		if(this.getIdGenre() != null && this.getIdGenre() != 0){		
			filtre.addFiltre(FilmFilterBy.genre,this.getIdGenre());
		}
		if(this.getIdPays() != null && this.getIdPays() != 0){		
			filtre.addFiltre(FilmFilterBy.pays,this.getIdPays());
		}
		if(this.getTitreLike() != null && !this.getTitreLike().isEmpty()){
			filtre.addFiltreText(FilmFilterText.titre, this.getTitreLike());
		}
		return filtre;
	}
	
	/**
	 * charge les listes deroulantes des filtres en fonction des films chargés dans la liste
	 * tri des listes affichés
	 * @param listeFilms
	 */
	@Override
	protected void chargerListeDeroulante(List<Film> listeFilms){
		//Chargement des listes
		realisateurs = filmService.getRealisateurByListeFilm(listeFilms);
		genres = filmService.getGenreByListeFilm(listeFilms);		
		pays = filmService.getPaysByListeFilm(listeFilms);
		
		// Tri des listes
		Collections.sort(genres, new GenreComparateur());
		Collections.sort(realisateurs, new PersonneComparateur());
		Collections.sort(pays, new PaysComparateur());
	}
 
	@Override
	protected void checkFiltre() {
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
	public void setOrderBy(IOrderBy orderBy) {
		this.orderBy = (FilmOrderBy) orderBy;		
	}
	
	@Override
	public FilmOrderBy[] getListOrderBy() {
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
			setClearFiltre(null);
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
			setClearFiltre(null);
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
			setClearFiltre(null);
		this.idPays = idPays;
	}	
	
	public String getTitreLike() {
		return titreLike;
	}

	public void setTitreLike(String titreLike) {
		this.titreLike = titreLike;
	}
}

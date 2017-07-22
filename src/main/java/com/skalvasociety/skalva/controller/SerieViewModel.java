package com.skalvasociety.skalva.controller;

import java.util.Collections;
import java.util.List;

import com.skalvasociety.skalva.bean.FiltreSerie;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.comparateur.GenreComparateur;
import com.skalvasociety.skalva.bean.comparateur.PaysComparateur;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.SerieFilterBy;
import com.skalvasociety.skalva.enumeration.SerieOrderBy;
import com.skalvasociety.skalva.service.ISerieService;

public class SerieViewModel {
	private SerieOrderBy serieOrderBy;
	private int currentPage;
	private int totalPage;
	private List<Serie> series;
	
	private List<Genre> genres;
	private Integer idGenre;
	
	private List<Pays> pays;
	private Integer idPays;
	
	private SerieFilterBy clearFiltre;
	
	private ISerieService serieService;

	
	private static final int PAGE_SIZE = 18;
	private static final int DEFAULT_PAGE = 1;
	private static final SerieOrderBy DEFAULT_SORT = SerieOrderBy.note;
	
	
	public SerieViewModel(ISerieService serieService){		
		this.serieService = serieService;
		initialisation();    	 
	}
	
	private void initialisation(){
		setCurrentPage(DEFAULT_PAGE);
		setSerieOrderBy(DEFAULT_SORT);
    	
    	// Chargement des resultats par defaut pour l'initialisation
    	PageRequest<Serie> pageRequest = new PageRequest<Serie>(DEFAULT_PAGE, PAGE_SIZE, DEFAULT_SORT.getSortDirectionDefaut(), DEFAULT_SORT);    	
    	this.setSeries( serieService.getAllByFiltrePage(pageRequest, new FiltreSerie()));
    	this.setTotalPage(pageRequest.getTotalPage());
    	
    	chargerListeDeroulante(serieService.getAll());
	}
	
	public void refreshModel() {		
		PageRequest<Serie> pageRequest = new PageRequest<Serie>(this.getCurrentPage(), PAGE_SIZE, this.getSerieOrderBy().getSortDirectionDefaut(), this.getSerieOrderBy());			
		
		checkFiltre();
		FiltreSerie filtreSerie = new FiltreSerie();

		if(this.getIdGenre() != null && this.getIdGenre() != 0){		
			filtreSerie.addFiltre(SerieFilterBy.genre,this.getIdGenre());
		}
		if(this.getIdPays() != null && this.getIdPays() != 0){		
			filtreSerie.addFiltre(SerieFilterBy.pays,this.getIdPays());
		}
			
		series = serieService.getAllByFiltrePage(pageRequest, filtreSerie);
		this.setSeries(series);
		this.setTotalPage(pageRequest.getTotalPage());  
		
		chargerListeDeroulante(serieService.getByFiltre(filtreSerie));
	}

	private void checkFiltre() {
		if(getClearFiltre() != null){
			switch (getClearFiltre()){
			case pays:
				this.setIdPays(null);
				break;
			case genre:
				this.setIdGenre(null);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * charge les listes deroulantes des filtres en fonction des series chargés dans la liste
	 * @param listeSerie
	 */
	private void chargerListeDeroulante(List<Serie> listeSerie){
		//Chargement des listes
		
		genres = serieService.getGenreByListeSerie(listeSerie);		
		pays = serieService.getPaysByListeSerie(listeSerie);
		
		// Tri des listes
		Collections.sort(genres, new GenreComparateur());
		Collections.sort(pays, new PaysComparateur());
	}


	public int getCurrentPage() {
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
	
	public int getBeginPagination() {
		return Math.max(1, getCurrentPage() - 5);
	}

	public int getEndPagination() {
		return Math.min(getBeginPagination() + 10, getTotalPage());
	}


	public List<Serie> getSeries() {
		return series;
	}


	public void setSeries(List<Serie> series) {
		this.series = series;
	}


	public List<Genre> getGenres() {
		return genres;
	}


	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}


	public List<Pays> getPays() {
		return pays;
	}


	public void setPays(List<Pays> pays) {
		this.pays = pays;
	}


	public Integer getIdGenre() {
		return idGenre;
	}


	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	public Integer getIdPays() {
		return idPays;
	}

	public void setIdPays(Integer idPays) {
		this.idPays = idPays;
	}


	public SerieOrderBy getSerieOrderBy() {
		return serieOrderBy;
	}


	public void setSerieOrderBy(SerieOrderBy serieOrderBy) {
		this.serieOrderBy = serieOrderBy;
	}
	
	public SerieOrderBy [] getListSerieOrderBy() {
		return SerieOrderBy.values();
	}

	public SerieFilterBy getClearFiltre() {
		return clearFiltre;
	}

	public void setClearFiltre(SerieFilterBy clearFiltre) {
		this.clearFiltre = clearFiltre;
	}
}

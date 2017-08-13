package com.skalvasociety.skalva.controller;

import java.util.Collections;
import java.util.List;

import com.skalvasociety.skalva.bean.FiltreSerie;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.comparateur.GenreComparateur;
import com.skalvasociety.skalva.bean.comparateur.PaysComparateur;
import com.skalvasociety.skalva.enumeration.OrderBy;
import com.skalvasociety.skalva.enumeration.SerieFilterBy;
import com.skalvasociety.skalva.enumeration.SerieOrderBy;
import com.skalvasociety.skalva.service.ISerieService;

public class SerieViewModel extends AbstractListFiltreModel<Serie, SerieFilterBy> {

	
	private List<Genre> genres;
	private Integer idGenre;
	
	private List<Pays> pays;
	private Integer idPays;
	
	private ISerieService serieService;
	
	public SerieViewModel(ISerieService service){	
		super(service);
		this.serieService = service;
		setOrderBy(SerieOrderBy.titre);
		initialisation();    	 
	}	

	@Override
	protected void checkFiltre() {
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
	@Override
	protected void chargerListeDeroulante(List<Serie> listeSerie){
		//Chargement des listes
		
		genres = serieService.getGenreByListeSerie(listeSerie);		
		pays = serieService.getPaysByListeSerie(listeSerie);
		
		// Tri des listes
		Collections.sort(genres, new GenreComparateur());
		Collections.sort(pays, new PaysComparateur());
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

	@Override
	IFiltre<Serie> chargerCriteresFiltre() {
		FiltreSerie filtre = new FiltreSerie();
		if(this.getIdGenre() !=  null && this.getIdGenre() != 0){
			filtre.addFiltre(SerieFilterBy.genre, this.getIdGenre());
		}
		if(this.getIdPays() != null && this.getIdPays() != 0){
			filtre.addFiltre(SerieFilterBy.pays, this.getIdPays());
		}
		return filtre;
	}

	@Override
	public SerieOrderBy getOrderBy() {
		return (SerieOrderBy) orderBy;
	}

	@Override
	public void setOrderBy(OrderBy orderBy) {		
		this.orderBy = (SerieOrderBy)orderBy;
	}

	@Override
	public SerieOrderBy[] getListOrderBy() {		
		return SerieOrderBy.values();
	}

	@Override
	protected void chargerGraphe() {
		// TODO Auto-generated method stub
		
	}
}

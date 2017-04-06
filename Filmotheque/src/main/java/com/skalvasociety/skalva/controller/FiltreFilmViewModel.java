package com.skalvasociety.skalva.controller;

import java.util.List;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.enumeration.SortBy;

public class FiltreFilmViewModel {
	private SortBy sortBy;
	private int currentPage;
	private List<Realisateur> realisateurs;
	private Integer idRealisateur;

	public SortBy getSortBy() {
		return sortBy;
	}

	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
		this.idRealisateur = idRealisateur;
	}
	
	

	
	

}

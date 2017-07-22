package com.skalvasociety.skalva.controller;

import java.util.List;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.ActeurOrderBy;

import com.skalvasociety.skalva.service.IActeurService;


public class ActeurViewModel {

	private int currentPage;
	private int totalPage;
	private List<Acteur> acteurs;	

	private IActeurService acteurService;
	
	private static final int PAGE_SIZE = 18;
	private static final int DEFAULT_PAGE = 1;
	private static final ActeurOrderBy DEFAULT_ACTEUR_SORT = ActeurOrderBy.nom;
	
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

	public List<Acteur> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	public int getBeginPagination() {
		return Math.max(1, getCurrentPage() - 5);
	}

	public int getEndPagination() {
		return Math.min(getBeginPagination() + 10, getTotalPage());
	}
	
	public ActeurViewModel(IActeurService acteurService){
		this.acteurService = acteurService;
		initialisation();    	 
	}
	
	private void initialisation(){
		setCurrentPage(DEFAULT_PAGE);		
    	
    	// Chargement des resultats par defaut pour l'initialisation
    	PageRequest<Acteur> pageRequest = new PageRequest<Acteur>(DEFAULT_PAGE, PAGE_SIZE, DEFAULT_ACTEUR_SORT.getSortDirectionDefaut(), DEFAULT_ACTEUR_SORT);    	
    	this.setActeurs(acteurService.getAllByPage(pageRequest));
    	this.setTotalPage(pageRequest.getTotalPage());  	
	}
	
	public void refreshModel() {		
		PageRequest<Acteur> pageRequest = new PageRequest<Acteur>(this.getCurrentPage(), PAGE_SIZE, DEFAULT_ACTEUR_SORT.getSortDirectionDefaut(), DEFAULT_ACTEUR_SORT);		
			
		acteurs = acteurService.getAllByPage(pageRequest);
		this.setActeurs(acteurs);
		this.setTotalPage(pageRequest.getTotalPage());
	}
}

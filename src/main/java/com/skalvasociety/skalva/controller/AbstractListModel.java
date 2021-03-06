package com.skalvasociety.skalva.controller;

import java.util.List;

import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.IOrderBy;
import com.skalvasociety.skalva.service.IService;

public abstract class AbstractListModel<T> {
	private int currentPage;
	private int totalPage;
	protected List<T> liste;	

	protected IService<Integer, T> service;
	
	protected static final int PAGE_SIZE = 18;
	protected static final int DEFAULT_PAGE = 1;
	protected IOrderBy orderBy ;
	
	abstract protected IOrderBy getOrderBy();

	abstract protected void setOrderBy(IOrderBy orderBy);
	
	protected abstract IOrderBy [] getListOrderBy();
	

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

	public List<T> getListe() {
		return liste;
	}

	public void setListe(List<T> liste) {
		this.liste = liste;
	}

	public int getBeginPagination() {
		return Math.max(1, getCurrentPage() - 5);
	}

	public int getEndPagination() {
		return Math.min(getBeginPagination() + 10, getTotalPage());
	}
	
	@SuppressWarnings("unchecked")
	public AbstractListModel(IService<?, T> service){
		this.service = (IService<Integer, T>) service;			 
	}
	
	protected void initialisation(){
		setCurrentPage(DEFAULT_PAGE);		
    	
    	// Chargement des resultats par defaut pour l'initialisation
    	PageRequest<T> pageRequest = new PageRequest<T>(DEFAULT_PAGE, PAGE_SIZE, getOrderBy().getSortDirectionDefaut(), getOrderBy());    	
    	this.setListe(service.getAllByPage(pageRequest));
    	this.setTotalPage(pageRequest.getTotalPage());
    	chargerGraphe();
	}
	
	public void refreshModel() {		
		PageRequest<T> pageRequest = new PageRequest<T>(this.getCurrentPage(), PAGE_SIZE, getOrderBy().getSortDirectionDefaut(), getOrderBy());
		this.setListe(service.getAllByPage(pageRequest));
		this.setTotalPage(pageRequest.getTotalPage());
		chargerGraphe();
	}
	/**
	 * Implementer les graphes à afficher dans la vue
	 */
	abstract protected void chargerGraphe();

}

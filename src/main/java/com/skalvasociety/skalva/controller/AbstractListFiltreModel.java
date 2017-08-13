package com.skalvasociety.skalva.controller;

import java.util.List;

import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.FilterBy;
import com.skalvasociety.skalva.service.IService;

public abstract class AbstractListFiltreModel<T, FILTRE extends FilterBy > extends AbstractListModel<T> {
	
	private FILTRE clearFiltre;

	public AbstractListFiltreModel(IService<?, T> service) {
		super(service);
	}
	
	@Override
	protected void initialisation() {
		super.initialisation();
		chargerListeDeroulante(liste);
	}
	
	

	@Override
	public void refreshModel() {
		checkFiltre();		
		IFiltre<T> filtre = chargerCriteresFiltre();
		
		PageRequest<T> pageRequest = new PageRequest<T>(this.getCurrentPage(), PAGE_SIZE, this.getOrderBy().getSortDirectionDefaut(), this.getOrderBy());
		liste = service.getAllByFiltrePage(pageRequest, filtre);	
		List<T> listeFiltre = service.getByFiltre(filtre);
		this.setListe(liste);
		this.setTotalPage(pageRequest.getTotalPage()); 
		chargerListeDeroulante(listeFiltre);
		chargerGraphe();
	}

	/**
	 * Charger les liste des menus déroulants de la classe fille
	 */
	abstract void chargerListeDeroulante(List<T> liste);
	
	/**
	 * Reinitialiser les critères de filtres selon le retour de getClearFiltre()
	 */
	abstract void checkFiltre();
	
	/**
	 * 
	 * @return
	 */
	abstract IFiltre<T> chargerCriteresFiltre();

	public FILTRE getClearFiltre() {
		return clearFiltre;
	}

	public void setClearFiltre(FILTRE clearFiltre) {
		this.clearFiltre = clearFiltre;
	}
}

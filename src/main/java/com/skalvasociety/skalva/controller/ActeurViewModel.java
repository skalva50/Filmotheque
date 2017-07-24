package com.skalvasociety.skalva.controller;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.enumeration.ActeurOrderBy;
import com.skalvasociety.skalva.enumeration.OrderBy;
import com.skalvasociety.skalva.service.IActeurService;


public class ActeurViewModel extends AbstractListModel<Acteur> {
	
	public ActeurViewModel(IActeurService service) {		
		super(service);		
		setOrderBy(ActeurOrderBy.nom);
		initialisation();
	}
	
	@Override
	public ActeurOrderBy getOrderBy() {
		return (ActeurOrderBy) orderBy;		
	}

	@Override
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = (ActeurOrderBy) orderBy;		
	}
	
	public ActeurOrderBy [] getListOrderBy() {
		return ActeurOrderBy.values();
	}
}

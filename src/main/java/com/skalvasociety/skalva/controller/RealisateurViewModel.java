package com.skalvasociety.skalva.controller;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.enumeration.OrderBy;
import com.skalvasociety.skalva.enumeration.RealisateurOrderBy;
import com.skalvasociety.skalva.service.IRealisateurService;

public class RealisateurViewModel extends AbstractListModel<Realisateur> {

	public RealisateurViewModel(IRealisateurService service) {
		super(service);
		setOrderBy(RealisateurOrderBy.nom);
		initialisation();		
	}
	
	@Override
	public RealisateurOrderBy getOrderBy() {
		return (RealisateurOrderBy) orderBy;		
	}

	@Override
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = (RealisateurOrderBy) orderBy;		
	}
	
	@Override
	public RealisateurOrderBy [] getListOrderBy() {
		return RealisateurOrderBy.values();
	}

	@Override
	protected void chargerGraphe() {
		// TODO Auto-generated method stub
		
	}
}

package com.skalvasociety.skalva.controller;

import java.util.List;

import com.skalvasociety.skalva.bean.FiltreRealisateur;
import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.enumeration.IOrderBy;
import com.skalvasociety.skalva.enumeration.RealisateurFilterBy;
import com.skalvasociety.skalva.enumeration.RealisateurFilterText;
import com.skalvasociety.skalva.enumeration.RealisateurOrderBy;
import com.skalvasociety.skalva.service.IRealisateurService;

public class RealisateurViewModel extends AbstractListFiltreModel<Realisateur, RealisateurFilterBy> {
	
	String nomLike;


	public RealisateurViewModel(IRealisateurService service) {
		super(service);
		setOrderBy(RealisateurOrderBy.popularite);
		initialisation();		
	}
	
	@Override
	public RealisateurOrderBy getOrderBy() {
		return (RealisateurOrderBy) orderBy;		
	}

	@Override
	public void setOrderBy(IOrderBy orderBy) {
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
	
	public String getNomLike() {
		return nomLike;
	}

	public void setNomLike(String nomLike) {
		this.nomLike = nomLike;
	}

	@Override
	void chargerListeDeroulante(List<Realisateur> liste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void checkFiltre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	IFiltre<Realisateur> chargerCriteresFiltre() {
		FiltreRealisateur filtre = new FiltreRealisateur();
		
		if(this.getNomLike() != null && !this.getNomLike().isEmpty()){
			filtre.addFiltreText(RealisateurFilterText.nom, this.getNomLike());
		}
		return filtre;
	}
}

package com.skalvasociety.skalva.controller;

import java.util.List;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.FiltreActeur;
import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.bean.Sexe;
import com.skalvasociety.skalva.enumeration.ActeurOrderBy;
import com.skalvasociety.skalva.enumeration.OrderBy;
import com.skalvasociety.skalva.enumeration.ActeurFilterBy;
import com.skalvasociety.skalva.service.IActeurService;
import com.skalvasociety.skalva.service.ISexeService;


public class ActeurViewModel extends AbstractListFiltreModel<Acteur, ActeurFilterBy> {
	
	private List<Sexe> sexes;
	private Integer idSexe;	
	
	private ISexeService sexeService;

	
	public ActeurViewModel(IActeurService service, ISexeService sexeService) {		
		super(service);	
		this.sexeService = sexeService;
		setOrderBy(ActeurOrderBy.nom);
		initialisation();		
	}

	@Override
	protected IFiltre<Acteur> chargerCriteresFiltre() {
		FiltreActeur filtre = new FiltreActeur();
		if(this.getIdSexe() !=  null && this.getIdSexe() !=  0){
			filtre.addFiltre(ActeurFilterBy.sexe,this.getIdSexe());						
		}
		return filtre;
	}
	
	

	@Override
	protected void checkFiltre() {
		if(getClearFiltre() != null){
			switch (getClearFiltre()){
			case sexe:
				this.setIdSexe(0);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void chargerListeDeroulante(List<Acteur> liste) {			
		sexes = sexeService.getAll();		
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

	public List<Sexe> getSexes() {
		return sexes;
	}

	public void setSexes(List<Sexe> sexes) {
		this.sexes = sexes;
	}

	public Integer getIdSexe() {
		return idSexe;
	}

	public void setIdSexe(Integer idSexe) {
		if(idSexe != null)
			setClearFiltre(null);
		this.idSexe = idSexe;
	}

	@Override
	protected void chargerGraphe() {
		// TODO Auto-generated method stub
		
	}
}

package com.skalvasociety.skalva.bean;

import java.util.HashMap;

public abstract class AbstractFiltre<FilterBy> {
	protected HashMap<FilterBy,Integer> listeFiltre = new HashMap<FilterBy, Integer>();
	
	public HashMap<FilterBy,Integer> getListeFiltre() {
		return listeFiltre;
	}

	public void setListeFiltre(HashMap<FilterBy,Integer> listeFiltre) {
		this.listeFiltre = listeFiltre;
	}
	
	public void addFiltre (FilterBy filtre, Integer id){
		HashMap<FilterBy, Integer> listeFiltre = getListeFiltre();
		listeFiltre.put(filtre,id);
		setListeFiltre(listeFiltre);			
	}
}

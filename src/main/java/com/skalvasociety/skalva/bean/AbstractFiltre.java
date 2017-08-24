package com.skalvasociety.skalva.bean;

import java.util.HashMap;

public abstract class AbstractFiltre<IFilterBy, IFilterText> {
	protected HashMap<IFilterBy,Integer> listeFiltre = new HashMap<IFilterBy, Integer>();
	protected HashMap<IFilterText, String> listeFiltreText = new HashMap<IFilterText, String>();
	
	public HashMap<IFilterBy,Integer> getListeFiltre() {
		return listeFiltre;
	}

	public void setListeFiltre(HashMap<IFilterBy,Integer> listeFiltre) {
		this.listeFiltre = listeFiltre;
	}
	
	public void addFiltre (IFilterBy filtre, Integer id){
		HashMap<IFilterBy, Integer> listeFiltre = getListeFiltre();
		listeFiltre.put(filtre,id);
		setListeFiltre(listeFiltre);			
	}

	public HashMap<IFilterText, String> getListeFiltreText() {
		return listeFiltreText;
	}

	public void setListeFiltreText(HashMap<IFilterText, String> listeFiltreText) {
		this.listeFiltreText = listeFiltreText;
	}
	
	public void addFiltreText (IFilterText filtreText, String text){
		HashMap<IFilterText, String> listeFiltreText = getListeFiltreText();
		listeFiltreText.put(filtreText,text);
		setListeFiltreText(listeFiltreText);			
	}
}

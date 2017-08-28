package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import com.skalvasociety.skalva.enumeration.RealisateurFilterBy;
import com.skalvasociety.skalva.enumeration.RealisateurFilterText;

public class FiltreRealisateur extends AbstractFiltre<RealisateurFilterBy, RealisateurFilterText> implements IFiltre<Realisateur> {

	public List<Realisateur> filtrerListe(List<Realisateur> listeAFiltrer) {
		List<Realisateur> listToRemove = new LinkedList<Realisateur>();	
		
		for (RealisateurFilterText entite : listeFiltreText.keySet()) {
			switch(entite){
			case nom:
				for (Realisateur realisateur : listeAFiltrer) {
					if(!realisateur.getNom().toUpperCase().contains(listeFiltreText.get(entite).toUpperCase())){
						listToRemove.add(realisateur);
					}
				}
			}			
		}
		listeAFiltrer.removeAll(listToRemove);		
		return listeAFiltrer;

	}

}

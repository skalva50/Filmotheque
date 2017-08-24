package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import com.skalvasociety.skalva.enumeration.SerieFilterBy;
import com.skalvasociety.skalva.enumeration.SerieFilterText;

public class FiltreSerie extends AbstractFiltre<SerieFilterBy, SerieFilterText> implements IFiltre<Serie> {

	public List<Serie> filtrerListe(List<Serie> listeAFiltrer) {
		List<Serie> listToRemove = new LinkedList<Serie>();	
		
		for (SerieFilterBy filtre : listeFiltre.keySet()) {
			switch (filtre) {
			case genre:
				for (Serie serie : listeAFiltrer) {
					List<Genre> genres = serie.getGenres();
					boolean find = false;
					for (Genre genre : genres) {
						if(genre.getId() == listeFiltre.get(filtre)){
							find = true;
							break;
						}
					}
					if(!find){
						listToRemove.add(serie);
					}
				}	
				break;
			case pays:
				for (Serie serie : listeAFiltrer) {
					List<Pays> paysS = serie.getPays();
					boolean find = false;
					for (Pays pays : paysS) {
						if(pays.getId() == listeFiltre.get(filtre)){
							find = true;
							break;
						}
					}
					if(!find){
						listToRemove.add(serie);
					}
				}	;
				break;
			default:
				break;
			}
		}
		
		// Filtrer par texte sur une entite
		for (SerieFilterText entite : listeFiltreText.keySet()) {
			switch(entite){
			case titre:
				for (Serie serie : listeAFiltrer) {
					if(!serie.getTitre().toUpperCase().contains(listeFiltreText.get(entite).toUpperCase())){
						listToRemove.add(serie);
					}
				}
			}			
		}
		listeAFiltrer.removeAll(listToRemove);
		listToRemove.clear();
		return listeAFiltrer;
	}
}

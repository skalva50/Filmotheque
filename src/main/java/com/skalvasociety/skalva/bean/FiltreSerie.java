package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import com.skalvasociety.skalva.enumeration.SerieFilterBy;

public class FiltreSerie extends AbstractFiltre<SerieFilterBy> implements IFiltre<Serie> {

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
				listeAFiltrer.removeAll(listToRemove);
				listToRemove.clear();
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
				}	
				listeAFiltrer.removeAll(listToRemove);
				listToRemove.clear();
				break;
			default:
				break;
			}
		}
		return listeAFiltrer;
	}
}

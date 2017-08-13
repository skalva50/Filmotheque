package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.enumeration.FilmFilterBy;

@Transactional
public class FiltreFilm extends AbstractFiltre<FilmFilterBy> implements IFiltre<Film>{	


	public List<Film> filtrerListe(List<Film> listeAFiltrer) {
		List<Film> listToRemove = new LinkedList<Film>();	
		
		for (FilmFilterBy filtre : listeFiltre.keySet()) {
			switch (filtre) {
			case realisateur:
				for (Film film : listeAFiltrer) {				
					List<Realisateur> realisateurs = film.getRealisateurs();
					boolean find = false;
					for (Realisateur realisateur : realisateurs) {
						if(realisateur.getId() == listeFiltre.get(filtre)){							
							find = true;
							break;
						}
					}
					if(!find && !listToRemove.contains(film)){
						listToRemove.add(film);
					}
				}
				break;
			case genre:
				for (Film film : listeAFiltrer) {
					List<Genre> genres = film.getGenres();
					boolean find = false;
					for (Genre genre : genres) {
						if(genre.getId() == listeFiltre.get(filtre)){
							find = true;
							break;
						}
					}
					if(!find && !listToRemove.contains(film)){
						listToRemove.add(film);
					}
				}	
				break;
			case pays:
				for (Film film : listeAFiltrer) {
					List<Pays> paysS = film.getPays();
					boolean find = false;
					for (Pays pays : paysS) {
						if(pays.getId() == listeFiltre.get(filtre)){
							find = true;
							break;
						}
					}
					if(!find && !listToRemove.contains(film)){
						listToRemove.add(film);
					}
				}
				break;
			default:
				break;
			}
		}
		listeAFiltrer.removeAll(listToRemove);
		listToRemove.clear();
		return listeAFiltrer;
	}
}

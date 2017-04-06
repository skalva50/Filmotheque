package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class FiltreFilm {
	private boolean byGenre =  false;
	private boolean byRealisateur = false;
	private Integer idGenre;
	private Integer idRealisateur;

	public boolean isByGenre() {
		return byGenre;
	}

	public void setByGenre(boolean byGenre) {
		this.byGenre = byGenre;
	}
	
	public boolean isByRealisateur() {
		return byRealisateur;
	}

	public void setByRealisateur(boolean byRealisateur) {
		this.byRealisateur = byRealisateur;
	}

	
	public Integer getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	
	public Integer getIdRealisateur() {
		return idRealisateur;
	}

	public void setIdRealisateur(Integer idRealisateur) {
		this.idRealisateur = idRealisateur;
	}

	public List<Film> filmFilter (List<Film> filmAll){
		List<Film> filmFilter = new LinkedList<Film>();
		if(isByGenre()){
			for (Film film : filmAll) {
				List<Genre> genres = film.getGenres();
				for (Genre genre : genres) {
					if(genre.getId() == getIdGenre()){
						if(!filmFilter.contains(film))
							filmFilter.add(film);
						break;
					}
				}
			}			
		}
		if(isByRealisateur()){
			for (Film film : filmAll) {
				List<Realisateur> realisateurs = film.getRealisateurs();
				for (Realisateur realisateur : realisateurs) {
					if(realisateur.getId() == getIdRealisateur()){
						if(!filmFilter.contains(film))
							filmFilter.add(film);
						break;
					}
				}
			}			
		}
		
		
		return filmFilter;		
	}

}

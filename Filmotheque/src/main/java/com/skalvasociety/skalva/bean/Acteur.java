package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ACTEUR")
public class Acteur extends Personne {

	private List<FilmPersonnage> filmPersonnages = new LinkedList<FilmPersonnage>();
	private List<SeriePersonnage> seriePersonnages = new LinkedList<SeriePersonnage>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acteur")
	public List<FilmPersonnage> getFilmPersonnages() {
		return filmPersonnages;
	}
	public void setFilmPersonnages(List<FilmPersonnage> filmPersonnages) {
		this.filmPersonnages = filmPersonnages;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acteur")
	public List<SeriePersonnage> getSeriePersonnages() {
		return seriePersonnages;
	}
	public void setSeriePersonnages(List<SeriePersonnage> seriePersonnages) {
		this.seriePersonnages = seriePersonnages;
	}
	
	

	
	
	
	
}

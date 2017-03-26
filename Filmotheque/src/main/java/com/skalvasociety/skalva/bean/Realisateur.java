package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="REALISATEUR")
public class Realisateur extends Personne {
	
	private List<Film> films = new LinkedList<Film>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "realisateurs")
	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	
}

package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="GENRE")
public class Genre extends EntiteTmdb {
	private String libelle;
	private List<Film> films = new LinkedList<Film>();
	private List<Serie> series = new LinkedList<Serie>();

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	
	
}

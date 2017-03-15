package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
public class Film extends MediaTMDB {	
		
	private Fichier fichier;	
	private Double popularite;
	private Double note;	
	private List<Genre> genres = new LinkedList<Genre>();

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfichier", nullable = false)
	public Fichier getFichier() {
		return fichier;
	}

	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}
	

	public Double getPopularite() {
		return popularite;
	}

	public void setPopularite(Double popularite) {
		this.popularite = popularite;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "film_genre", 
			joinColumns = {@JoinColumn(name = "idfilm", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "idgenre",	nullable = false, updatable = false) })
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

}

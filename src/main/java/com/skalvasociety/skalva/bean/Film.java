package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
@PrimaryKeyJoinColumn(name = "id")
public class Film extends MediaTMDB {			
	private Fichier fichier;
	private Double popularite;
	private Double note;	
	private Integer duree;
	private List<Genre> genres = new LinkedList<Genre>();
	private List<FilmPersonnage> personnages = new LinkedList<FilmPersonnage>();
	private List<Realisateur> realisateurs = new LinkedList<Realisateur>();
	
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "film_genre", 
			joinColumns = {@JoinColumn(name = "idfilm", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "idgenre",	nullable = false, updatable = false) })
	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
	public List<FilmPersonnage> getPersonnages() {
		return personnages;
	}

	public void setPersonnages(List<FilmPersonnage> personnages) {
		this.personnages = personnages;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "film_realisateur", 
			joinColumns = {@JoinColumn(name = "idfilm", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "idrealisateur",	nullable = false, updatable = false) })
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}	
}

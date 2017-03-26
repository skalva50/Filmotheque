package com.skalvasociety.skalva.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILM_PERSONNAGE")
public class FilmPersonnage extends Entite {
	private Acteur acteur;
	private Film film;
	private String nom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idacteur", nullable = false)
	public Acteur getActeur() {
		return acteur;
	}
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idfilm", nullable = false)
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}

package com.skalvasociety.skalva.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SERIE_PERSONNAGE")
public class SeriePersonnage extends Entite {
	
	private Acteur acteur;
	private Serie serie;
	private String nom;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idActeur", nullable = false)
	public Acteur getActeur() {
		return acteur;
	}
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSerie", nullable = false)
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}

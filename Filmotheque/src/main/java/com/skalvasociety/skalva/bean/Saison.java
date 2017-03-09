package com.skalvasociety.skalva.bean;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SAISON")
public class Saison {
	private int id;	
	private int numero;
	private String resume;
	private String affiche;
	private String dateSortie;
	private Serie serie;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getAffiche() {
		return affiche;
	}
	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSerie", nullable = false)
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
}

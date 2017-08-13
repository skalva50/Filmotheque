package com.skalvasociety.skalva.bean;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Personne extends EntiteTmdb {
	private String nom;
	private String photo;
	private String biographie;
	private Date dateNaissance;
	private Date DateDeces;
	private String lieuNaissance;
	private Sexe sexe;
	private Double popularite;
	
	public Double getPopularite() {
		return popularite;
	}
	public void setPopularite(Double popularite) {
		this.popularite = popularite;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getBiographie() {
		return biographie;
	}
	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Date getDateDeces() {
		return DateDeces;
	}
	public void setDateDeces(Date dateDeces) {
		DateDeces = dateDeces;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSexe", nullable = true)
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}	
	
}

package com.skalvasociety.skalva.bean;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Personne extends EntiteTmdb {
	private String nom;
	private String photo;
	private String biographie;
	private String dateNaissance;
	private String DateDeces;
	private String lieuNaissance;
	
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
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getDateDeces() {
		return DateDeces;
	}
	public void setDateDeces(String dateDeces) {
		DateDeces = dateDeces;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}	
	
	
}

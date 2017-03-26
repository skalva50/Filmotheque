package com.skalvasociety.skalva.bean;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Personne extends EntiteTmdb {
	private String nom;
	private String photo;
	

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
}

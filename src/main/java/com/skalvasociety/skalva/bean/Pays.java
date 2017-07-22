package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PAYS")
public class Pays extends Entite {
	private String idIso;
	private String nom;
	private List<MediaTMDB> listMedia = new LinkedList<MediaTMDB>();
	
	public String getIdIso() {
		return idIso;
	}
	public void setIdIso(String idIso) {
		this.idIso = idIso;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pays")
	public List<MediaTMDB> getListMedia() {
		return listMedia;
	}
	public void setListMedia(List<MediaTMDB> listMedia) {
		this.listMedia = listMedia;
	}
	
	
}

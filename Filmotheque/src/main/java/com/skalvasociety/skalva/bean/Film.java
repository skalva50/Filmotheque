package com.skalvasociety.skalva.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
public class Film extends MediaTMDB {	
		
	private Fichier fichier;	
	private Double popularite;
	private Double note;	

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

}

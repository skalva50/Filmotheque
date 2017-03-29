package com.skalvasociety.skalva.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="EPISODE")
@PrimaryKeyJoinColumn(name = "id")
public class Episode extends MediaTMDB {	
	private Fichier fichier;
	private Integer numero;
	private Saison saison;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfichier", nullable = false)
	public Fichier getFichier() {
		return fichier;
	}

	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}

	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSaison", nullable = false)
	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}
	
	
}

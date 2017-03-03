package com.skalvasociety.skalva.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILM")
public class Film {	
	
    private int id;	
	private int idTMDB;	
	private Fichier fichier;
	
	private String titre;
	private String titreOriginal;
	private String resume;
	private String affiche;
	
	private String cleVideo;
	private String siteVideo;
	
	private Double popularite;
	private Double note;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTMDB() {
		return idTMDB;
	}

	public void setIdTMDB(int idTMDB) {
		this.idTMDB = idTMDB;
	}


	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idfichier", nullable = false)
	public Fichier getFichier() {
		return fichier;
	}

	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTitreOriginal() {
		return titreOriginal;
	}

	public void setTitreOriginal(String titreOriginal) {
		this.titreOriginal = titreOriginal;
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

	public String getCleVideo() {
		return cleVideo;
	}

	public void setCleVideo(String cleVideo) {
		this.cleVideo = cleVideo;
	}

	public String getSiteVideo() {
		return siteVideo;
	}

	public void setSiteVideo(String siteVideo) {
		this.siteVideo = siteVideo;
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

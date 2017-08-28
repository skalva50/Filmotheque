package com.skalvasociety.skalva.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MediaTMDB extends EntiteTmdb {
	
	private String titre;
	private String titreOriginal;
	private String resume;
	private String affiche;	
	private String cleVideo;
	private String siteVideo;
	private String resumeCourt;
	private Date dateSortie;
	private Date dateAjout;
	private List<Pays> pays = new LinkedList<Pays>();
	private List<Video> videos = new LinkedList<Video>();

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
	public String getResumeCourt() {
		return resumeCourt;
	}
	public void setResumeCourt(String resumeCourt) {
		this.resumeCourt = resumeCourt;
	}
	public Date getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
	public List<Video> getVideos() {
		return videos;
	}	
	
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "media_pays", 
			joinColumns = {@JoinColumn(name = "idMedia", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "idPays",	nullable = false, updatable = false) })
	public List<Pays> getPays() {
		return pays;
	}
	public void setPays(List<Pays> pays) {
		this.pays = pays;
	}	
}

package com.skalvasociety.skalva.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
	private String dateSortie;
	
	private List<Video> videos;

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
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}	
}

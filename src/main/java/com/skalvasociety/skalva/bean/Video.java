package com.skalvasociety.skalva.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VIDEO")
public class Video extends Entite {
	private String cleVideo;
	private String siteVideo;
	private String nom;
	private MediaTMDB media;	
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMedia", nullable = false)
	public MediaTMDB getMedia() {
		return media;
	}
	
	public void setMedia(MediaTMDB media) {
		this.media = media;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}

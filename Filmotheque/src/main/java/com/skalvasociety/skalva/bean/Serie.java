package com.skalvasociety.skalva.bean;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="SERIE")
@PrimaryKeyJoinColumn(name = "id")
public class Serie extends MediaTMDB {
		
	private Double popularite;
	private Double note;	
	private List<Saison> saison = new LinkedList<Saison>();
	private List<Genre> genres = new LinkedList<Genre>();
	private List<SeriePersonnage> personnages = new LinkedList<SeriePersonnage>();
	private List<Realisateur> realisateurs = new LinkedList<Realisateur>();
	private static final int RESUME_COURT_LONG = 50;
	

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serie")
	public List<Saison> getSaison() {
		return saison;
	}
	public void setSaison(List<Saison> saison) {
		this.saison = saison;
	}
	
	@Override
	public void setResumeCourt(String resume) {
		int endIndex = RESUME_COURT_LONG;
		if(resume ==  null)
			return;
		
		if (resume.length() <= endIndex){
			endIndex = resume.length();
		}		
		super.setResumeCourt(resume.substring(0, endIndex)+"...");
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "serie_genre", 
			joinColumns = {@JoinColumn(name = "idserie", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "idgenre",	nullable = false, updatable = false) })
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serie")
	public List<SeriePersonnage> getPersonnages() {
		return personnages;
	}
	public void setPersonnages(List<SeriePersonnage> personnages) {
		this.personnages = personnages;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "serie_realisateur", 
			joinColumns = {@JoinColumn(name = "idserie", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "idrealisateur",	nullable = false, updatable = false) })
	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}
	
}


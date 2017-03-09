package com.skalvasociety.skalva.bean;


import java.util.LinkedList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SERIE")
public class Serie {
	
	private int id;	
	private int idTMDB;
	private String titre;
	private String titreOriginal;
	private String resume;
	private String affiche;
	private Double popularite;
	private Double note;	
	private List<Saison> saison = new LinkedList<Saison>();
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIdTMDB() {
		return idTMDB;
	}
	public void setIdTMDB(int idTMDB) {
		this.idTMDB = idTMDB;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serie")
	public List<Saison> getSaison() {
		return saison;
	}
	public void setSaison(List<Saison> saison) {
		this.saison = saison;
	}
	
	
}

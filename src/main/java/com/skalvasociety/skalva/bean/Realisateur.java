package com.skalvasociety.skalva.bean;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="REALISATEUR")
public class Realisateur extends Personne {
	
	private List<Film> films = new LinkedList<Film>();
	
	@Transient
	private String concatLibelleNbFilms;
	
	@Transient
	private int nbFilms;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "realisateurs")
	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
		if (films != null) {
			this.nbFilms = films.size();
		}
		
	}
	
	@Transient
	public String getConcatLibelleNbFilms(){
		String concat =  getNom();
		int longNom = getNom().length();
		int longConcat =  longNom;		
		do {
			concat = concat +"-";
			longConcat++;
		} while (longConcat < 40);
		concat = concat +"> "+ nbFilms+" Film(s)";
		return concat;
	}
	
	@Transient
	public void setConcatLibelleNbFilms(String concatLibelleNbFilms) {
		
	}
}

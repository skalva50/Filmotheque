package com.skalvasociety.skalva.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="FICHIER")
public class Fichier {
	

    private int id;

    private String chemin;
	
	private Film film;
	private Episode episode;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty
	@Size(min=3, max=500)
    @Column(name = "chemin", unique=true, nullable = false)
	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "fichier")
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "fichier")
	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}
}

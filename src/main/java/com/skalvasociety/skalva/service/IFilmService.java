package com.skalvasociety.skalva.service;

import java.io.Serializable;
import java.util.List;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Realisateur;

public interface IFilmService extends IService<Serializable, Film> {
	public String getDureeFormatee(Film film);	
	public List<Realisateur> getRealisateurByListeFilm(List<Film> listeFilm);
	public List<Genre> getGenreByListeFilm(List<Film> listeFilm);
	public List<Pays> getPaysByListeFilm(List<Film> listeFilm);
	public void majFilmByIdTMDB(Integer idFilm, Integer idTMDB);
}

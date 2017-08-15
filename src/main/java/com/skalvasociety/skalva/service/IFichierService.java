package com.skalvasociety.skalva.service;

import java.io.Serializable;
import com.skalvasociety.skalva.bean.Fichier;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.tmdbObject.MovieDetails;

public interface IFichierService extends IService<Serializable, Fichier> {	
	boolean isFichierCheminUnique(String chemin);
	public void movieDetailsToFilm(MovieDetails movieDetail, Film film);
	void majFichier();
}

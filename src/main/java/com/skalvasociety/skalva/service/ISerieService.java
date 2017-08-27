package com.skalvasociety.skalva.service;

import java.io.Serializable;
import java.util.List;

import com.skalvasociety.skalva.bean.Genre;
import com.skalvasociety.skalva.bean.Pays;
import com.skalvasociety.skalva.bean.Serie;


public interface ISerieService extends IService<Serializable, Serie> {
	public void majSerie();
	public List<Genre> getGenreByListeSerie(List<Serie> listeSerie);
	public List<Pays> getPaysByListeSerie(List<Serie> listeSerie);
	public boolean idTMDBExists(Serie serie);
	public void majSerieByIdTMDB(Integer idSerie,Integer idTMDB);	
	public void deleteSerieObsolete();
}

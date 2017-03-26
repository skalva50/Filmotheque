package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Serie;

public interface ISerieService {
	public void saveSerie(Serie serie);
	public void majSerie();
	public List<Serie> getAllSeries();
	public Serie getSerieById(Integer idSerie);
	public boolean idTMDBExists(Serie serie);
}

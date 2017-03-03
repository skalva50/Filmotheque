package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Serie;


public interface ISerieDao {
	void saveSerie(Serie serie);
	List<Serie> findAllSeries();
	Serie getSerieById(Integer idSerie);

}

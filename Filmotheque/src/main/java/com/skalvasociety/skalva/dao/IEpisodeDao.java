package com.skalvasociety.skalva.dao;

import java.util.List;

import com.skalvasociety.skalva.bean.Episode;

public interface IEpisodeDao {
	void saveEpisode(Episode episode);
	List<Episode> getEpisodes();
	public Episode getEpisodeById(Integer idEpisode);
}
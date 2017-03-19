package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Episode;

public interface IEpisodeService {
	void saveEpisode(Episode episode);
	List<Episode> getEpisodes();
	public Episode getEpisodeById(Integer idEpisode);
}

package com.skalvasociety.skalva.service;

import java.util.List;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.tmdbObject.EpisodeTMDB;

public interface IEpisodeService {
	void saveEpisode(Episode episode);
	List<Episode> getEpisodes();
	Episode getEpisodeById(Integer idEpisode);
	Episode getEpisodeBySaisonNumEpisode (Saison saison, Integer numEpisode);
	void episodeTmdbToEpisode(EpisodeTMDB episodeTMDB, Episode episode);
}

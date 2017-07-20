package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.tmdbObject.EpisodeTMDB;

public interface IEpisodeService extends IService<Serializable, Episode>{	
	Episode getEpisodeBySaisonNumEpisode (Saison saison, Integer numEpisode);
	void episodeTmdbToEpisode(EpisodeTMDB episodeTMDB, Episode episode);
}

package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Saison;

public interface IEpisodeDao extends IDao<Serializable, Episode> {
	Episode getEpisodeBySaisonNumEpisode (Saison saison, Integer numEpisode);
}

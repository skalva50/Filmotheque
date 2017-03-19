package com.skalvasociety.skalva.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Episode;


@Repository("episodeDao")
public class EpisodeDao extends AbstractDao<Integer, Episode> implements IEpisodeDao {

	public void saveEpisode(Episode episode) {
		persist(episode);

	}

	@SuppressWarnings("unchecked")
	public List<Episode> getEpisodes() {
		Criteria criteria = createEntityCriteria();
		return (List<Episode>) criteria.list();
	}
	
	public Episode getEpisodeById(Integer idEpisode){
		Episode episode = getByKey(idEpisode);
		episode.getSaison().getNumero();
		episode.getSaison().getSerie().getTitre();
		return episode;
	}

}

package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.bean.Saison;

@Repository("episodeDao")
public class EpisodeDao extends AbstractDao<Integer, Episode> implements IEpisodeDao {

	public Episode getEpisodeBySaisonNumEpisode(Saison saison, Integer numEpisode) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("saison", saison));
        criteria.add(Restrictions.eq("numero", numEpisode));
        return (Episode) criteria.uniqueResult();
	}
}

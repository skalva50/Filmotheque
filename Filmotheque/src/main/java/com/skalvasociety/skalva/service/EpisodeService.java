package com.skalvasociety.skalva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Episode;
import com.skalvasociety.skalva.dao.IEpisodeDao;



@Service("episodeService")
@Transactional
public class EpisodeService implements IEpisodeService {
	
	@Autowired
	IEpisodeDao episodeDao;
	public void saveEpisode(Episode episode) {
		episodeDao.saveEpisode(episode);

	}

	public List<Episode> getEpisodes() {		
		return episodeDao.getEpisodes();
	}

	public Episode getEpisodeById(Integer idEpisode) { 
		return episodeDao.getEpisodeById(idEpisode);
	}

}

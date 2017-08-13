package com.skalvasociety.skalva.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;


@Repository("saisonDao")
public class SaisonDao extends AbstractDao<Integer, Saison> implements ISaisonDao {

	
	@Override
	public void save(Saison saison) {
		if(isUnique(saison)){
			super.save(saison);
		}else if (saison != null){
			saison = getSaisonByIdSerieNumSaison(saison.getSerie(), saison.getNumero());
		}		
	}
	
	public boolean isUnique(Saison saison){
		if (saison == null || saison.getSerie() == null)
			return true;
		if(getSaisonByIdSerieNumSaison(saison.getSerie(), saison.getNumero()) == null){
			return true;
		}else{
			return false;
		}
	}
	
	public Saison getSaisonByIdSerieNumSaison(Serie serie, Integer numSaison) {		
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("serie", serie));
        criteria.add(Restrictions.eq("numero", numSaison));
        return (Saison) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Saison> getSaisonByIdSerie(Serie serie) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("serie", serie));
		return (List<Saison>) criteria.list();
	}	
	/*
	public Saison getSaisonById(Integer idSaison){
		Criteria criteria = createEntityCriteria();
		criteria.setFetchMode("episodes", FetchMode.JOIN);
		criteria.setFetchMode("serie", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", idSaison));      
        Saison saison = (Saison) criteria.uniqueResult();
        if(saison !=  null && saison.getEpisodes() != null){
        	List<Episode> episodes = saison.getEpisodes();
        	Collections.sort(episodes, new EpisodeComparateur());
        	saison.setEpisodes(episodes);       	
        }
		return saison;
	}*/

}

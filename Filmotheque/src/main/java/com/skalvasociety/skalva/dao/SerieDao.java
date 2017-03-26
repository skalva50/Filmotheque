package com.skalvasociety.skalva.dao;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Saison;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.comparateur.SaisonComparateur;

@Repository("serieDao")
public class SerieDao extends AbstractDao<Integer, Serie>  implements ISerieDao{

	public void saveSerie(Serie serie) {
		persist(serie);
		
	}

	@SuppressWarnings("unchecked")
	public List<Serie> findAllSeries() {
		Criteria criteria = createEntityCriteria();
		criteria.setFetchMode("genres", FetchMode.JOIN);
		criteria.addOrder(Order.desc("note"));
		List<Serie> series = criteria.list();
        List<Serie> seriesReduce = new LinkedList<Serie>();
        for (Serie serie : series) {
			if(!seriesReduce.contains(serie)){
				seriesReduce.add(serie);
			}
		}
        return seriesReduce;
	}


	public Serie getSerieById(Integer idSerie) {		
		Criteria criteria = createEntityCriteria();
		criteria.setFetchMode("saison", FetchMode.JOIN);  
        criteria.add(Restrictions.eq("id", idSerie));      
        Serie serie = (Serie) criteria.uniqueResult();
        if(serie !=  null && serie.getSaison() != null){
        	List<Saison> saisons = serie.getSaison();
        	Collections.sort(saisons, new SaisonComparateur());
        	serie.setSaison(saisons);        	
        }        
        return serie;
	}
	
	public Serie getSerieByIdTMDB(Integer idTMDB) {		
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTMDB));        
        return (Serie) criteria.uniqueResult();
	}

	public boolean idTMDBExists(Serie serie) {		
		if(serie == null)
			return false;
        if (getSerieByIdTMDB(serie.getIdTMDB()) == null){
        	return false;        
       	}	
        else{
        	return true;
        }
        	
	}

}

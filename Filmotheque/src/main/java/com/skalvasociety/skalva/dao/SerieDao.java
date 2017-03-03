package com.skalvasociety.skalva.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Serie;

@Repository("serieDao")
public class SerieDao extends AbstractDao<Integer, Serie>  implements ISerieDao{

	public void saveSerie(Serie serie) {
		persist(serie);
		
	}

	@SuppressWarnings("unchecked")
	public List<Serie> findAllSeries() {
		Criteria criteria = createEntityCriteria();	
		criteria.addOrder(Order.desc("note"));
        return (List<Serie>) criteria.list();
	}

	public Serie getSerieById(Integer idSerie) {
		return getByKey(idSerie);
	}

}

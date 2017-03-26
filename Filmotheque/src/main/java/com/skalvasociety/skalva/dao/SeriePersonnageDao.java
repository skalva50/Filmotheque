package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Serie;
import com.skalvasociety.skalva.bean.SeriePersonnage;

@Repository("SeriePersonnageDao")
public class SeriePersonnageDao extends AbstractDao<Integer, SeriePersonnage> implements ISeriePersonnageDao {

	public void saveSeriePersonnage(SeriePersonnage seriePersonnage) {
		persist(seriePersonnage);

	}

	public SeriePersonnage getSeriePersonnagebySerieActeur(Serie serie, Acteur acteur) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("serie", serie));   
        criteria.add(Restrictions.eq("acteur", acteur));   
		return (SeriePersonnage)criteria.uniqueResult();
	}

}

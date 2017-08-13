package com.skalvasociety.skalva.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Acteur;


@Repository("acteurDao")
public class ActeurDao extends AbstractDao<Integer, Acteur> implements IActeurDao {

	public Acteur getActeurIdTMDB(Integer idTmdb) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTmdb));        
		return (Acteur)criteria.uniqueResult();
	}
}

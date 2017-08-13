package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Pays;

@Repository("paysDao")
public class PaysDao extends AbstractDao<Integer, Pays> implements IPaysDao {

	public Pays getPaysbyIdIso(String idIso) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idIso", idIso));        
		return (Pays)criteria.uniqueResult();
	}
}

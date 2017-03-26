package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Realisateur;

@Repository("realisateurDao")
public class RealisateurDao extends AbstractDao<Integer, Realisateur> implements IRealisateurDao {

	public void saveRealisateur(Realisateur realisateur) {
		persist(realisateur);

	}

	public Realisateur getRealisateurByIdTMDB(Integer idTMDB) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTMDB));        
		return (Realisateur)criteria.uniqueResult();
	}

}

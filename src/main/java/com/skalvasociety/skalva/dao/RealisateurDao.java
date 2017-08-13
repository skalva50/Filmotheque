package com.skalvasociety.skalva.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.bean.comparateur.RealisateurComparateurNbFilms;

@Repository("realisateurDao")
public class RealisateurDao extends AbstractDao<Integer, Realisateur> implements IRealisateurDao {

	public Realisateur getRealisateurByIdTMDB(Integer idTMDB) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTMDB));        
		return (Realisateur)criteria.uniqueResult();
	}

	public List<Realisateur> getAllOrderByNbFilms(){
		List<Realisateur> all = getAll();
		Collections.sort(all, new RealisateurComparateurNbFilms());		
		return all;		
	}
}

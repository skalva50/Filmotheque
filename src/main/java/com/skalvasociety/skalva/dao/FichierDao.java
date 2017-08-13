package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Fichier;

@Repository("fichierDao")
public class FichierDao extends AbstractDao<Integer, Fichier> implements IFichierDao{

	public Fichier findFichierByChemin(String chemin) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("chemin", chemin));
        return (Fichier) criteria.uniqueResult();
	}
}

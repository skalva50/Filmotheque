package com.skalvasociety.skalva.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Fichier;

@Repository("fichierDao")
public class FichierDao extends AbstractDao<Integer, Fichier> implements IFichierDao{

	public Fichier findById(int id) {		
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<Fichier> findAllFichiers() {
		Criteria criteria = createEntityCriteria();
        return (List<Fichier>) criteria.list();
	}

	public void saveFichier(Fichier fichier) {		
		persist(fichier);
	}

	public Fichier findFichierByChemin(String chemin) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("chemin", chemin));
        return (Fichier) criteria.uniqueResult();

	}

}

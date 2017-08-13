package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Acteur;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FilmPersonnage;

@Repository("FilmPersonnageDao")
public class FilmPersonnageDao extends AbstractDao<Integer, FilmPersonnage> implements IFilmPersonnageDao {

	public FilmPersonnage getFilmPersonnagebyFilmActeur(Film film, Acteur acteur) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("film", film));   
        criteria.add(Restrictions.eq("acteur", acteur));   
		return (FilmPersonnage)criteria.uniqueResult();
		
	}

}

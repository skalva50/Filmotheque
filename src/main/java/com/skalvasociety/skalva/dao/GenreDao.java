package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Genre;

@Repository("genreDao")
public class GenreDao extends AbstractDao<Integer, Genre> implements IGenreDao {	

	public Genre getGenreByIdTmdb(Integer idTmdb) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTmdb));        
		return (Genre)criteria.uniqueResult();
	}

}

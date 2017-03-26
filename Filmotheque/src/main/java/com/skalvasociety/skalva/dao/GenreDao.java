package com.skalvasociety.skalva.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Genre;

@Repository("genreDao")
public class GenreDao extends AbstractDao<Integer, Genre> implements IGenreDao {

	public void saveGenre(Genre genre) {
		persist(genre);
	}

	@SuppressWarnings("unchecked")
	public List<Genre> getGenres() {
		Criteria criteria = createEntityCriteria();		
		return (List<Genre>) criteria.list();
	}

	public Genre getGenreById(int id) {		
		return getByKey(id);
	}

	public Genre getGenreByIdTmdb(Integer idTmdb) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTmdb));        
		return (Genre)criteria.uniqueResult();
	}

}

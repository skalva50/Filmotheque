package com.skalvasociety.skalva.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Film;

@Repository("filmDao")
public class FilmDao extends AbstractDao<Integer, Film> implements IFilmDao {

	public void saveFilm(Film film) {
		persist(film);
	}

	@SuppressWarnings("unchecked")
	public List<Film> findAllFilms() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.desc("popularite"));
        return (List<Film>) criteria.list();
	}

	public Film getFilmById(Integer idFilm) {		
		return getByKey(idFilm);
	}

}

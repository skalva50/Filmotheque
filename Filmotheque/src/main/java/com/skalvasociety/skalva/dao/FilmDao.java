package com.skalvasociety.skalva.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;

@Repository("filmDao")
@Transactional
public class FilmDao extends AbstractDao<Integer, Film> implements IFilmDao {

	public void saveFilm(Film film) {
		persist(film);
	}

	@SuppressWarnings("unchecked")
	public List<Film> findAllFilms() {
		Criteria criteria = createEntityCriteria();
		criteria.setFetchMode("genres", FetchMode.JOIN);
		criteria.addOrder(Order.desc("note"));	
        List<Film> films = criteria.list();
        List<Film> filmsReduce = new LinkedList<Film>();
        for (Film film : films) {
			if(!filmsReduce.contains(film)){
				filmsReduce.add(film);
			}
		}
        return filmsReduce;
	}

	public Film getFilmById(Integer idFilm) {	
		Criteria criteria = createEntityCriteria();
		criteria.setFetchMode("personnages", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", idFilm));
		return (Film)criteria.uniqueResult();
	}

}

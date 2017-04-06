package com.skalvasociety.skalva.dao;

import java.util.LinkedList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.FiltreFilm;
import com.skalvasociety.skalva.bean.Realisateur;
import com.skalvasociety.skalva.daoTools.PageRequest;


@Repository("filmDao")
@Transactional
public class FilmDao extends AbstractDao<Integer, Film> implements IFilmDao {
	
	@Autowired
	IGenreDao genreDao;

	public void saveFilm(Film film) {
		persist(film);
	}

	public Film getFilmById(Integer idFilm) {	
		Criteria criteria = createEntityCriteria();
		criteria.setFetchMode("personnages", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", idFilm));
		return (Film)criteria.uniqueResult();
	}
	
	public List<Film> getFilmsPage(PageRequest<Film> pageRequest) {
		List<Film> films = getAllWithGraph(pageRequest.getSort(), pageRequest.getSortBy(), "genres");		
        List<Film> pageFilms = getPage(films, pageRequest);      
        return pageFilms;
	}
	
	public List<Film> getFilmsByRealisateurPage(PageRequest<Film> pageRequest,  Integer idRealisateur){
		List<Film> films = getAllWithGraph(pageRequest.getSort(), pageRequest.getSortBy(), "genres");
		List<Film> filmsByRealisateur  = new LinkedList<Film>();	
		
		for (Film film : films) {
			List<Realisateur> realisateurs = film.getRealisateurs();
			for (Realisateur realisateur : realisateurs) {
				if(realisateur.getId() == idRealisateur ){
					filmsByRealisateur.add(film);
					break;
				}
			}
		}		
		List<Film> pageFilms = getPage(filmsByRealisateur, pageRequest);
		return pageFilms;
	}
	
	public List<Film> getFilmsByGenrePage(PageRequest<Film> pageRequest, FiltreFilm filtre){		
		List<Film> filmsAll = getAllWithGraph(pageRequest.getSort(), pageRequest.getSortBy(), "genres");
		List<Film> filmsFiltre  = filtre.filmFilter(filmsAll);		
		List<Film> pageFilms = getPage(filmsFiltre, pageRequest);
		return pageFilms;
	}
}

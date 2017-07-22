package com.skalvasociety.skalva.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.Film;

@Repository("filmDao")
@Transactional
public class FilmDao extends AbstractDao<Integer, Film> implements IFilmDao
{

}

package com.skalvasociety.skalva.service;

import java.io.Serializable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.dao.IDao;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.IOrderBy;
import com.skalvasociety.skalva.enumeration.SortDirection;

public class AbstractService<PK extends Serializable, T> implements IService<Serializable,T> {
	
	@Autowired
	private IDao<Serializable,T> dao;
	
	public void save(T entite) {
		dao.save(entite);		
	}
	
	public void delete(T entity) {
		dao.delete(entity);		
	}

	public T getByKey(Serializable key) {
		return dao.getByKey(key);
	}

	public List<T> getAll() {
		return dao.getAll();		
	}

	public List<T> getAll(IOrderBy orderBy, SortDirection sort) {
		return dao.getAll(orderBy, sort);
	}

	public List<T> getByFiltre(IFiltre<T> filtre) {
		return dao.getByFiltre(filtre);		
	}

	public List<T> getAllByPage(PageRequest<T> pageRequest) {
		return dao.getAllByPage(pageRequest);		
	}

	public List<T> getAllByFiltrePage(PageRequest<T> pageRequest, IFiltre<T> filtre) {
		return dao.getAllByFiltrePage(pageRequest, filtre);
	}

	public T getByKeyWithGraph(Serializable key) {
		return dao.getByKeyWithGraph(key);		
	}

	public T getByKeyWithGraph(Serializable key, List<String> entites) {
		return dao.getByKeyWithGraph(key, entites);
	}
}

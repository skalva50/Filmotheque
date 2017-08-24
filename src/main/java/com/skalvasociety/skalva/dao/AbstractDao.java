package com.skalvasociety.skalva.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaetmoi.core.persistence.hibernate.LazyLoadingUtil;
import com.skalvasociety.skalva.bean.IFiltre;
import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.SortDirection;
import com.skalvasociety.skalva.enumeration.IOrderBy;

public abstract class AbstractDao<PK extends Serializable, T> implements IDao<Serializable,T>{

	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    
	@SuppressWarnings("unchecked")
	public T getByKey(Serializable key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void save(T entity) {
        getSession().persist(entity);
    }

 
    public void delete(T entity) {
        getSession().delete(entity);
    }
    
	public T getByKeyWithGraph(Serializable key) {
		T entite = getByKey(key);
		LazyLoadingUtil.deepHydrate(getSession(), entite);       
        return entite;
	}
	
	@SuppressWarnings("unchecked")
	public T getByKeyWithGraph(Serializable key, List<String> entites) {
		Criteria criteria = createEntityCriteria();
		for (String entite : entites) {
			criteria.setFetchMode(entite, FetchMode.JOIN);
		}			
		criteria.add(Restrictions.eq("id", key));
		return (T) criteria.uniqueResult();
	}
	
    
    public List<T> getPage(List<T> listToPage, PageRequest<T> pageRequest) {
    	List<T> page = new LinkedList<T>();    	
        int firstIndex = (pageRequest.getPageNumber()-1)*pageRequest.getPageSize();
        int lastIndex = pageRequest.getPageNumber() * pageRequest.getPageSize();        
        if(listToPage.size() == 0 || firstIndex < 0){
        	firstIndex = 0;
        }
        page = listToPage.subList(Math.min(firstIndex,Math.max(listToPage.size()-1,0)) , Math.min(Math.max(lastIndex,0), listToPage.size()));
        pageRequest.setTotalPage(listToPage);
		return page;
    }    

     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
    
	public List<T> getAllByPage(PageRequest<T> pageRequest) {
		List<T> listeAll = getAll(pageRequest.getSortBy(), pageRequest.getSort());
        List<T> listePage = getPage(listeAll, pageRequest);      
        return listePage;
	}
	
	public List<T> getAllByFiltrePage(PageRequest<T> pageRequest, IFiltre<T> filtre){	
		List<T> filmsAll = getAll(pageRequest.getSortBy(), pageRequest.getSort());
		List<T> filmsFiltre  = filtre.filtrerListe(filmsAll);		
		List<T> pageFilms = getPage(filmsFiltre, pageRequest);
		return pageFilms;
	}
    
	@SuppressWarnings("unchecked")
	public List<T> getAll(IOrderBy sortBy, SortDirection sort) {
		Criteria criteria = createEntityCriteria();		
		if(sort.equals(SortDirection.DESC)){
			criteria.addOrder(Order.desc(sortBy.toString()));
		}else{
			criteria.addOrder(Order.asc(sortBy.toString()));
		}			
        return (List<T>) criteria.list();
        
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Criteria criteria = createEntityCriteria();					
        return (List<T>) criteria.list();        
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllWithGraph(SortDirection sort, IOrderBy sortBy, String entiteAddGraph) {		
		Criteria criteria = createEntityCriteria();
		if(entiteAddGraph !=  null && !entiteAddGraph.equals("")){
			criteria.setFetchMode(entiteAddGraph, FetchMode.JOIN);
		}			
	
		if(sort.equals(SortDirection.DESC)){
			criteria.addOrder(Order.desc(sortBy.toString()));
		}else{
			criteria.addOrder(Order.asc(sortBy.toString()));
		}			
        List<T> list = criteria.list();
        List<T> listReduce = new LinkedList<T>();
        for (T entite : list) {
			if(!listReduce.contains(entite)){
				listReduce.add(entite);
			}
		}        
        return listReduce;
	}
	
	public List<T> getByFiltre (IFiltre<T> filtre){
		return filtre.filtrerListe(getAll());
	}
}
 
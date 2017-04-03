package com.skalvasociety.skalva.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.skalvasociety.skalva.daoTools.PageRequest;
import com.skalvasociety.skalva.enumeration.Sort;
import com.skalvasociety.skalva.enumeration.SortBy;

public abstract class AbstractDao<PK extends Serializable, T> {

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
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
    
    public List<T> getPage(List<T> listToPage, PageRequest pageRequest) {
    	List<T> page = new LinkedList<T>();    	
        int firstIndex = (pageRequest.getPageNumber()-1)*pageRequest.getPageSize();
        int lastIndex = pageRequest.getPageNumber() * pageRequest.getPageSize();
        page = listToPage.subList(Math.min(Math.max(firstIndex,0),listToPage.size()) , Math.min(Math.max(lastIndex,0), listToPage.size()));     	
		return page;
    }
    
    public int getTotalPage(List<T> listToPage, PageRequest pageRequest){
    	int totalPage = 0;
    	if (pageRequest.getPageSize() > 0){
    		totalPage= listToPage.size() / pageRequest.getPageSize()+1;
    	}    		
    	return totalPage;
    }
     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
    
    //Ajouter le critère de tri
	@SuppressWarnings("unchecked")
	public List<T> getAll(Sort sort, SortBy sortBy) {
		Criteria criteria = createEntityCriteria();		
		if(sort.equals(Sort.DESC)){
			//criteria.addOrder(Order.desc("note"));
		}else{
			//criteria.addOrder(Order.asc("note"));
		}			
        return (List<T>) criteria.list();
        
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllWithGraph(Sort sort, SortBy sortBy, String entiteAddGraph) {		
		Criteria criteria = createEntityCriteria();
		if(entiteAddGraph !=  null && !entiteAddGraph.equals("")){
			criteria.setFetchMode(entiteAddGraph, FetchMode.JOIN);
		}			
	
		if(sort.equals(Sort.DESC)){
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
	
}
 
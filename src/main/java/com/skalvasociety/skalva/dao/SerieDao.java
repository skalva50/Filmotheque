package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.Serie;



@Repository("serieDao")
public class SerieDao extends AbstractDao<Integer, Serie>  implements ISerieDao{

	public Serie getSerieByIdTMDB(Integer idTMDB) {		
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("idTMDB", idTMDB));        
        return (Serie) criteria.uniqueResult();
	}

	public boolean idTMDBExists(Serie serie) {		
		if(serie == null)
			return false;
        if (getSerieByIdTMDB(serie.getIdTMDB()) == null){
        	return false;        
       	}	
        else{
        	return true;
        }        	
	}

}

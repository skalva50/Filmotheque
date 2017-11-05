package com.skalvasociety.skalva.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.skalvasociety.skalva.bean.User;

@Repository("userDao")
public class UserDao extends AbstractDao<Integer, User> implements IUserDao {	

	public User getByIdentifiant(String identifiant) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("identifiant", identifiant));
        return (User) crit.uniqueResult();
	}
}

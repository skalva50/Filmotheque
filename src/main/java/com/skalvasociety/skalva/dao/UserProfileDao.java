package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.UserProfile;

@Transactional
@Repository("userProfileDao")
public class UserProfileDao extends AbstractDao<Integer, UserProfile> implements IUserProfileDao {

	public UserProfile getByType(String type) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
	}

	@Override
	public UserProfile getByKey(Serializable key) {
		return super.getByKey(key);
	}
	
	
}

package com.skalvasociety.skalva.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.UserProfile;
import com.skalvasociety.skalva.dao.IUserProfileDao;

@Service("userProfileService")
@Transactional
public class UserProfileService extends AbstractService<Integer, UserProfile> implements IUserProfileService {
	
	 @Autowired
	 IUserProfileDao dao;
	 
	public UserProfile getByType(String type) {		
		return dao.getByType(type);
	}
	

}

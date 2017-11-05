package com.skalvasociety.skalva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.User;
import com.skalvasociety.skalva.dao.IUserDao;

@Service("userService")
@Transactional
public class UserService extends AbstractService<Integer, User> implements IUserService {
	
	@Autowired
    private IUserDao dao;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    

	@Override
	public void save(User user) {
		if(!isExists("identifiant", user.getIdentifiant())){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			super.save(user);
		}		
	}

	public User getByIdentifiant(String identifiant) {
		return dao.getByIdentifiant(identifiant);
	}
	
    public void updateUser(User user) {
        User entity = dao.getByKey(user.getId());
        if(entity!=null){
            entity.setIdentifiant(user.getIdentifiant());
            entity.setPassword(passwordEncoder.encode(user.getPassword()));            
            entity.setUserProfiles(user.getUserProfiles());
        }
    }



	public boolean isExists(String uniqueProperties, String identifiant) {
		return dao.isExists(uniqueProperties, identifiant);
	}
}

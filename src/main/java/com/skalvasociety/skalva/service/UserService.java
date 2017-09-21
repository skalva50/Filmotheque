package com.skalvasociety.skalva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.User;
import com.skalvasociety.skalva.dao.IUserDao;

@Service("userService")
@Transactional
public class UserService extends AbstractService<Integer, User> implements IUserService {
	
	@Autowired
    private IUserDao dao;

	public User getByIdentifiant(String identifiant) {
		return dao.getByIdentifiant(identifiant);
	}
}

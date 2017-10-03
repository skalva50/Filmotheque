package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.User;


public interface IUserService extends IService<Serializable, User> {
	User getByIdentifiant(String identifiant);
	public void updateUser(User user) ;
	public boolean isExists(String uniqueProperties, String identifiant);
}

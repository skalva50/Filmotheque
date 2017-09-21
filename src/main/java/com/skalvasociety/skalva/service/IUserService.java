package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.User;


public interface IUserService extends IService<Serializable, User> {
	User getByIdentifiant(String identifiant);
}

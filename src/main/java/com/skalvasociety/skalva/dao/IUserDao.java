package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.User;

public interface IUserDao extends IDao<Serializable, User> {
	User getByIdentifiant(String identifiant);
}

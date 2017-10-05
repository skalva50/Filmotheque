package com.skalvasociety.skalva.dao;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.UserProfile;

public interface IUserProfileDao extends IDao<Serializable, UserProfile> {
	UserProfile getByType(String type);
}

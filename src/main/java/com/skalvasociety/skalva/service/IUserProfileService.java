package com.skalvasociety.skalva.service;

import java.io.Serializable;

import com.skalvasociety.skalva.bean.UserProfile;

public interface IUserProfileService extends IService<Serializable, UserProfile> {
	UserProfile getByType(String type);
}

package com.skalvasociety.skalva.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.skalvasociety.skalva.bean.UserProfile;
import com.skalvasociety.skalva.service.IUserProfileService;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
	
	@Autowired
    IUserProfileService userProfileService;
	
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String)element);
        UserProfile profile= userProfileService.getByKey(id);       
        return profile;
	}

}

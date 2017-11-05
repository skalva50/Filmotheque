package com.skalvasociety.skalva.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skalvasociety.skalva.bean.User;
import com.skalvasociety.skalva.bean.UserProfile;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	 @Autowired
	 private IUserService userService;
	 
	 private Logger logger = Logger.getLogger(CustomUserDetailsService.class);

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		User user = userService.getByIdentifiant(ssoId);        
        if(user==null){
            logger.error(new UsernameNotFoundException("Username not found").getMessage(), new UsernameNotFoundException("Username not found").getCause());
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getIdentifiant(), user.getPassword(), 
                 user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(UserProfile userProfile : user.getUserProfiles()){            
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }        
        return authorities;
    }

}

package com.skalvasociety.skalva.controller;

import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skalvasociety.skalva.bean.User;
import com.skalvasociety.skalva.bean.UserProfile;
import com.skalvasociety.skalva.service.IUserProfileService;
import com.skalvasociety.skalva.service.IUserService;

@Transactional
@Controller
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private IUserProfileService userProfileService;
	
	@Autowired
    MessageSource messageSource;
		
	@RequestMapping(value="/administration" ,method = RequestMethod.GET)
	public String listUser(ModelMap model){
		List<User> listUser = service.getAll();
		for (User user : listUser) {
			List<UserProfile> list = user.getUserProfiles();
			for (UserProfile userProfile : list) {
				userProfile.getType();
			}
		}
		model.addAttribute("listUser", listUser);
        return "administration";
	}
	
	@RequestMapping(value = "/administration/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);  
        model.addAttribute("update", false);
        
        List<UserProfile> listeProfiles = userProfileService.getAll();
        model.addAttribute("listeProfiles", listeProfiles);
        
        return "newuser";
    }
	
	
    @RequestMapping(value = "/administration/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid User user,
            BindingResult result, ModelMap model) 
    {
 
        if (result.hasErrors()) {            
            return "newuser";
        }
        if(service.isExists("identifiant", user.getIdentifiant())){
        	FieldError identifiantError =new FieldError(
        			"user",
        			"identifiant",
        			messageSource.getMessage(
        					"non.unique.identifiant",
        					new String[]{user.getIdentifiant()},
        					Locale.getDefault())
        			);
        	result.addError(identifiantError);
        	return "newuser";
        }
        
        service.save(user);       
        return "redirect:/administration";
    }   
    
    @RequestMapping(value = "/administration/updateUser", method = RequestMethod.GET)
    public String updateUser(
    			@RequestParam(value="idUser",required = true ) Integer idUser,
    			ModelMap model)
    {
    	
    	User user = service.getByKey(idUser);
    	if(user == null){
    		return "redirect:/administration";
    	}
		List<UserProfile> list = user.getUserProfiles();
		for (UserProfile userProfile : list) {
			userProfile.getType();
		}
        model.addAttribute("user", user);
        model.addAttribute("update", true);

        List<UserProfile> listeProfiles = userProfileService.getAll();
        model.addAttribute("listeProfiles", listeProfiles);
    	
    	return "newuser";
    }
    
    @RequestMapping(value = "/administration/updateUser", method = RequestMethod.POST)
    public String updateUser(
	    		@RequestParam(value="idUser",required = false ) Integer idUser,
	    		@Valid User user,
	            BindingResult result,
	            ModelMap model)
    {
 
        if (result.hasErrors()) {            
            return "newuser";
        }
        service.updateUser(user); 
        return "redirect:/administration";
    }  
    
    @RequestMapping(value = "/administration/deleteUser", method = RequestMethod.GET)
    public String deleteUser (
    		@RequestParam(value="idUser", required = true) Integer idUser    		
    		)
    {
    	User user = service.getByKey(idUser);
    	service.delete(user);
    	return "redirect:/administration";
    	
    }
    
    
}

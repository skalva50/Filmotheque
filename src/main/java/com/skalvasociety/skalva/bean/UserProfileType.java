package com.skalvasociety.skalva.bean;

public enum UserProfileType {
	USER("USER"),
    DBA("INSCRIT"),
    ADMIN("ADMIN");
     
    String userProfileType;
     
    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }
     
    public String getUserProfileType(){
        return userProfileType;
    }
}

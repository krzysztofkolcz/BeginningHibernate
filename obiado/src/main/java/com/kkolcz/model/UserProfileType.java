package com.kkolcz.model;
 
public enum UserProfileType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN"),
    REGISTERED("REGISTERED");
     
    String userProfileType;
     
    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }
     
    public String getUserProfileType(){
        return userProfileType;
    }
     
}

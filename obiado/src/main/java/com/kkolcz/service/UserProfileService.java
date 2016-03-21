package com.kkolcz.service;
 
import java.util.List;
 
import com.kkolcz.model.UserProfile;
 
 
public interface UserProfileService {
 
    UserProfile findById(int id);
 
    UserProfile findByType(String type);
     
    List<UserProfile> findAll();
     
}

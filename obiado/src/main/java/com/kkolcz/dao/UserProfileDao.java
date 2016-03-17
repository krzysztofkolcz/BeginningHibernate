package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.UserProfile;
 
public interface UserDao {
 
    UserProfile findById(int id);
    UserProfile findByType(String type);
    List<UserProfile> findAll();
     
}

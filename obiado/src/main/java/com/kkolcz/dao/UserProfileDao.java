package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.UserProfile;
 
public interface UserProfileDao {
 
    UserProfile findById(int id);
    UserProfile findByType(String type);
    List<UserProfile> findAll();
    void persist(UserProfile profile);
    void save(UserProfile profile);
    void removeAll();
     
}

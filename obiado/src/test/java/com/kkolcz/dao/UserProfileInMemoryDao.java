package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.UserProfile;
 
public class UserProfileInMemoryDao extends AbstractInMemoryDao<UserProfile> implements UserProfileDao {
 
    public UserProfile findById(int id){
      return this.getByKey(id);
    }

    public UserProfile findByType(String type){
        for(UserProfile up : dao){
           if(up.getType()==type){
             return up;
           } 
        }
        return null;
    }

    public List<UserProfile> findAll(){
      return this.dao;
    }

    public void save(UserProfile userProfile){
      super.save(userProfile);
    }

    public void persist(UserProfile userProfile){
      super.save(userProfile);
    }
     
}

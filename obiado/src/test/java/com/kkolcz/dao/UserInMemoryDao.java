package com.kkolcz.dao;

import com.kkolcz.model.User;
import com.kkolcz.dao.UserDao;
import java.util.List;
import java.util.ArrayList;
 
public class UserInMemoryDao extends AbstractInMemoryDao<User> implements UserDao {

    public User findById(int id){
      return this.getByKey(id);
    }
     

    public User findByEmail(String email){
        for(User u : dao){
           if(u.getEmail()==email){
             return u;
           } 
        }
        return null;
    }

    public List<User> findAllUsers(){
        return this.dao;
    }

    public void persistUser(User user){
      this.persist(user);
    }

    public void saveUser(User user){
      this.save(user);
    }

}

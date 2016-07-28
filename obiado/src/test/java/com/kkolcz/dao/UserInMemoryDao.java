package com.kkolcz.dao;

import com.kkolcz.model.User;
import com.kkolcz.dao.UserDao;
import java.util.List;
import java.util.ArrayList;
 
public class UserInMemoryDao extends AbstractInMemoryDao<User> implements UserDao {

    public User findById(Integer id){
      return this.getByKey(id);
    }


    public List<User> findAll(){
        return this.dao;
    }

    /* TODO - implement comparison */
    public List<User> findAll(String order){
        return this.dao;
    }

    public void save(User user){
      this.save(user);
    }


    public void removeAll(){
      dao.removeAll(dao);
    }


    /* TODO - można będzie zastąpić funkcjią findByNaturalKey i findByNaturalKeyExceptId */
    public User findByEmail(String email){
        for(User u : dao){
           if(u.getEmail()==email){
             return u;
           } 
        }
        return null;
    }
    public List<User> findByEmailExceptId(String email,int id){
        List<User> found = new ArrayList<User>();
        for(User u : dao){
           if(u.getEmail()==email && u.getId()!=id){
             found.add(u);
           } 
        }
        return found;
    }

    /* TODO - ta funkcja tutaj nie ma sensu, jest potrzebna do implementacji UserDao. Zastąpić wszystkie te funkcje przez findByNaturalKey */
    public User findByName(String name){
      return null;
    }
    /* TODO - j.w. */
    public List<User> findByNameExceptId(String name,int id){
      return null;
    }
}

package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.User;
 
public interface UserDao extends Dao<User>{
    /* ZOSTAJE */
    public User findByEmail(String email) ;
    public List<User> findByEmailExceptId(String email,int id);


}

package com.kkolcz.dao;
 
import com.kkolcz.model.User;
 
public interface UserDao {
 
    User findById(int id);
     
    User findBySSO(String sso);

    User findByEmail(String email);
     
}

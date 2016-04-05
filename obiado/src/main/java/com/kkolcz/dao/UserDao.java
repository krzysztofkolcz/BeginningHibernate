package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.User;
 
public interface UserDao {
 
    User findById(int id);
     
    /* User findBySSO(String sso); */


    List<User> findAllUsers();
    void persistUser(User user);
    void saveUser(User user);
    void removeAll();
    /* void deleteBySSO(String sso); */
     
    public User findByEmail(String email) ;
    public List<User> findByEmailExpectId(String email,int id);
}

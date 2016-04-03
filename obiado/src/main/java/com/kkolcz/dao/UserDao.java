package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.User;
 
public interface UserDao {
 
    User findById(int id);
     
    /* User findBySSO(String sso); */

    User findByEmail(String email);

    List<User> findAllUsers();
    void persistUser(User user);
    void saveUser(User user);
    void removeAll();
    /* void deleteBySSO(String sso); */
     
    public List<User> findByEmailExpectId(String email,int id);
}

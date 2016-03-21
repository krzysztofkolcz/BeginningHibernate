package com.kkolcz.service;
 
import com.kkolcz.model.User;
import com.kkolcz.command.UserCommand;
import com.kkolcz.exception.EmailExistsException;
 
import java.util.List;

public interface UserService {
 
    User findById(int id);
    User findByEmail(String email);
    /* User findBySso(String sso); */
    /* void saveUser(User user); */
    /* void updateUser(User user); */
    /* void deleteUserBySSO(String sso); */
    List<User> findAllUsers(); 
    /* boolean isUserSSOUnique(Integer id, String sso); */

    /* http://www.baeldung.com/registration-with-spring-mvc-and-spring-security */
    User registerNewUserAccount(UserCommand userCommand) throws EmailExistsException;
     
}

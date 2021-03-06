package com.kkolcz.service;
 
import com.kkolcz.model.User;
import com.kkolcz.command.UserCommand;
import com.kkolcz.exception.EmailExistsException;
 
import java.util.List;

public interface UserService {
 
    User findById(int id);
    User findByEmail(String email);
    List<User> findAllUsers(); 

    /* http://www.baeldung.com/registration-with-spring-mvc-and-spring-security */
    User registerNewUserAccount(UserCommand userCommand) throws EmailExistsException;

    boolean emailExistExceptId(String email,int id);
    boolean emailExist(String email); 

    User addUser(UserCommand userCommand) throws EmailExistsException ;
    User updateUser(UserCommand userCommand) throws EmailExistsException ;
}

package com.kkolcz.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.kkolcz.dao.UserDao;
import com.kkolcz.dao.UserProfileDao;
import com.kkolcz.model.User;
import com.kkolcz.model.UserProfile;
import com.kkolcz.model.UserProfileType;
import com.kkolcz.command.UserCommand;
import com.kkolcz.exception.EmailExistsException;

import java.util.List;
import java.util.HashSet;
 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired private UserDao userDao;
    @Autowired private UserProfileDao userProfileDao;
 
    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
 
    /*
    public User findBySso(String sso) {
        return userDao.findBySSO(sso);
    }
    */

    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }

    /* for user registration - user has only REGISTERED profile */
    @Transactional
    @Override
    public User registerNewUserAccount(UserCommand userCommand) throws EmailExistsException {
        if (emailExist(userCommand.getEmail())) {  
            throw new EmailExistsException("There is an account with that email adress: " + userCommand.getEmail());
        }
        User user = new User();    
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        HashSet<UserProfile> userProfiles = new HashSet<UserProfile>();
        UserProfile userProfile = userProfileDao.findByType(UserProfileType.REGISTERED.getUserProfileType());
        userProfiles.add(userProfile);
        user.setUserProfiles(userProfiles);
        userDao.saveUser(user); 
        return user;
    }



    /* for administrator - user can have many profiles */
    @Transactional
    @Override
    public User addUser(UserCommand userCommand) throws EmailExistsException {
        if (emailExist(userCommand.getEmail())) {  
            throw new EmailExistsException("There is an account with that email adress: " + userCommand.getEmail());
        }
        User user = new User();    
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        user.setUserProfiles(userCommand.getUserProfiles());
        userDao.saveUser(user); 
        return user;
    }

    public boolean emailExist(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    /* for administrator - user can have many profiles */
    @Transactional
    @Override
    public User updateUser(UserCommand userCommand) throws EmailExistsException {
        if (emailExist(userCommand.getEmail())) {  
            throw new EmailExistsException("There is an account with that email adress: " + userCommand.getEmail());
        }
        User user = userDao.findById(userCommand.getId());    
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setPassword(userCommand.getPassword());
        user.setEmail(userCommand.getEmail());
        user.setUserProfiles(userCommand.getUserProfiles());
        userDao.saveUser(user); 
        return user;
    }
 
}

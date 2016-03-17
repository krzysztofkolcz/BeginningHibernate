package com.kkolcz.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.kkolcz.dao.UserDao;
import com.kkolcz.model.User;
 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao userDao;
 
    public User findById(int id) {
        return userDao.findById(id);
    }
 
    public User findBySso(String sso) {
        return userDao.findBySSO(sso);
    }

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
        HashSet<UserProfile> userProfiles = new HasSet<UserProfile>();
        userProfiles.add();
        setUserProfiles(Set<UserProfile> userProfiles)
        return userDao.save(user); 
    }

    private boolean emailExist(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
 
}

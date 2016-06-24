package com.kkolcz.dao;

import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.kkolcz.model.User;
 
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        /* websystique - hibernate many-to-many */
        User user = getByKey(id);
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }
 
    public List<User> findAll() {
      return super.findAll("firstName");
    }

    public User findByEmail(String email) {
        User user = super.findByFieldUnique("email",email);
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    public List<User> findByEmailExceptId(String email,int id){
        List<User> users = (List<User>)super.findByFieldExceptId("email",email,id);
        return users;
    }

    public void saveUser(User user) {
        save(user);
    }
 
    /* websystique - hibernate many-to-many */
    /*
    public void deleteBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        User user = (User)crit.uniqueResult();
        delete(user);
    }
    */

    public void removeAll(){
      //TODO
    }
}

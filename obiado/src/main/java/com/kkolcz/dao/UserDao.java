package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.User;
 
public interface UserDao extends Dao<User>{
 
    /* User findById(int id); */
    /* TODO */
    /* List<User> findAllUsers(); */
    /* TODO */
    void saveUser(User user);
    /* OK */
    void removeAll();
    /* ZOSTAJE */
    public User findByEmail(String email) ;
    public List<User> findByEmailExceptId(String email,int id);


    /* TODO - usunąć po odkomentowaniu extends Dao<User>*/

    /* public P findById(int id); */
    /* public List<P> findAll(); */
    /* public List<P> findAll(String orderBy); */
    /* public void persist(P p); */
    /* public void save(P p); */
    /* public void removeAll(); */

    /* TODO ? */
    /* public P findByName(String name); */
    /* TODO ? */
    /* public List<P> findByNameExceptId(String name,int id); */
}

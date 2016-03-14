package com.kkolcz.service;
 
import com.kkolcz.model.User;
 
public interface UserService {
 
    User findById(int id);
     
    User findBySso(String sso);
     
}

package com.kkolcz.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkolcz.service.UserService;
import com.kkolcz.service.UserServiceImpl;
import com.kkolcz.model.User;


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

  public static final String MODEL_ATTRIBUTE_USER_LIST = "userList";
  public static final String VIEW_USER_LIST = "admin/userList";

  @Autowired private UserService userService;


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String homePage(ModelMap model){
    return "admin";
  }

  @RequestMapping(value = "/userList", method = RequestMethod.GET)
  public String userListPage(ModelMap model){
    List<User> userList = userService.findAllUsers();
    model.addAttribute(MODEL_ATTRIBUTE_USER_LIST,userList);
    return VIEW_USER_LIST;
  }

}

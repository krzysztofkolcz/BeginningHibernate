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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.kkolcz.service.UserService;
import com.kkolcz.service.UserServiceImpl;
import com.kkolcz.service.UserProfileService;
import com.kkolcz.service.UserProfileServiceImpl;
import com.kkolcz.model.User;
import com.kkolcz.model.UserProfile;
import com.kkolcz.command.UserCommand;
import com.kkolcz.exception.EmailExistsException;


@Controller
@RequestMapping("/admin")
@SessionAttributes("roles")
public class AdminController extends BaseController{

  public static final String MODEL_ATTRIBUTE_USER_LIST = "userList";
  public static final String MODEL_ATTRIBUTE_USER_COMMAND = "userCommand";
  public static final String MODEL_ATTRIBUTE_ADMIN = "admin";
  public static final String VIEW_USER_LIST = "admin/userList";
  public static final String VIEW_REGISTER = "register";
  public static final String VIEW_SUCCESS_REGISTER = "successRegister";

  public static final String VIEW_USER_ADD= "admin/userEdit";
  public static final String VIEW_SUCCESS_USER_ADD= "admin/userEdit";

  @Autowired private UserService userService;
  @Autowired private UserProfileService userProfileService;


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String homePage(ModelMap model){
    return "admin";
  }

  @RequestMapping(value = "/user-list", method = RequestMethod.GET)
  public String userListPage(ModelMap model){
    List<User> userList = userService.findAllUsers();
    model.addAttribute(MODEL_ATTRIBUTE_USER_LIST,userList);
    return VIEW_USER_LIST;
  }

  @RequestMapping(value = "/add-user", method = RequestMethod.GET)
  public String addUser(ModelMap model){
    UserCommand userCommand = new UserCommand();
    model.addAttribute(MODEL_ATTRIBUTE_USER_COMMAND,userCommand);
    model.addAttribute(MODEL_ATTRIBUTE_ADMIN,true);
    return VIEW_USER_ADD;
  }


  /* just like AppController.register */
  @RequestMapping(value = "/add-user", method = RequestMethod.POST)
  public ModelAndView register( 
      @ModelAttribute("userCommand") @Valid UserCommand userCommand, 
      BindingResult result, 
      WebRequest request) {
      User registered = new User();
      if (!result.hasErrors()) {
          try {
              registered = userService.addUser(userCommand);
          } catch (EmailExistsException e) {
              result.rejectValue("email", "message.regError");
          }
      }
      if (result.hasErrors()) {
          return new ModelAndView(VIEW_USER_ADD, MODEL_ATTRIBUTE_USER_COMMAND, userCommand);
      } 
      else {
          return new ModelAndView(VIEW_SUCCESS_USER_ADD,MODEL_ATTRIBUTE_USER_COMMAND , userCommand);
      }
  }

  /**
   * This method will provide UserProfile list to views
   */
  @ModelAttribute("roles")
  public List<UserProfile> initializeProfiles() {
      return userProfileService.findAll();
  }

  @RequestMapping(value = "/edit-user-{userId}", method = RequestMethod.GET)
  public ModelAndView editUser(@PathVariable String userId,ModelMap model) {
      int id = Integer.parseInt(userId);
      User user = userService.findById(id);
      UserCommand userCommand = new UserCommand(user);

      model.addAttribute(MODEL_ATTRIBUTE_USER_COMMAND,userCommand);
      model.addAttribute(MODEL_ATTRIBUTE_ADMIN,true);

      return new ModelAndView(VIEW_USER_ADD, MODEL_ATTRIBUTE_USER_COMMAND, userCommand);
  }

  @RequestMapping(value = "/edit-user-{userId}", method = RequestMethod.POST)
  public String editUser(
      @ModelAttribute("userCommand") @Valid UserCommand userCommand, 
      BindingResult result, 
      WebRequest request,
      @PathVariable String userId) {

      if(result.hasErrors()){
        if(userService.emailExist(userCommand.getEmail())) {
            result.rejectValue("email", "message.regError");
        }
        return VIEW_USER_ADD;
      }

      try {
          userService.updateUser(userCommand);
      } catch (EmailExistsException e) {
          /* nie powinno wystąpić - powyżej sprawdzenie maila */
          result.rejectValue("email", "message.regError");
          return VIEW_USER_ADD;
      }

      return VIEW_USER_LIST;
  }
}

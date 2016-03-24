package com.kkolcz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.ModelAndView;
import com.kkolcz.model.User;
import com.kkolcz.command.UserCommand;
import com.kkolcz.service.UserService;
import com.kkolcz.service.UserProfileService;
import com.kkolcz.exception.EmailExistsException;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AppController extends BaseController{

    @Autowired UserService userService;
    @Autowired UserProfileService userProfileService;

    /* @Autowired */
    /* public AppController(UserService userService, UserProfileService uerProfileService){ */
    /*   this.userService = userService; */
    /*   this.userProfileService = userProfileService; */
    /* } */

    public static final String MODEL_ATTRIBUTE_USER_COMMAND = "userCommand";
    public static final String VIEW_REGISTER = "register";
    public static final String VIEW_SUCCESS_REGISTER = "successRegister";

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(ModelMap model) {
        UserCommand user = new UserCommand();
        model.addAttribute(MODEL_ATTRIBUTE_USER_COMMAND,user);
        return VIEW_REGISTER;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register( 
        @ModelAttribute("userCommand") @Valid UserCommand userCommand, 
        BindingResult result, 
        WebRequest request) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userCommand, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView(VIEW_REGISTER, MODEL_ATTRIBUTE_USER_COMMAND, userCommand);
        } 
        else {
            return new ModelAndView(VIEW_SUCCESS_REGISTER,MODEL_ATTRIBUTE_USER_COMMAND , userCommand);
        }
    }


    private User createUserAccount(UserCommand userCommand, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userCommand);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}

package com.kkolcz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kkolcz.model.User;

@Controller
public class AppController extends BaseController{

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
    public String registerPage() {
        UserCommand user = new UserCommand();
        model.addAttribute("user",user);
        return "register";
    }

    public ModelAndView register( 
        @ModelAttribute("user") @Valid UserCommand userCommand, 
        BindingResult result, 
        WebRequest request, 
        Errors errors) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userCommand, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", userCommand);
        } 
        else {
            return new ModelAndView("successRegister", "user", userCommand);
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

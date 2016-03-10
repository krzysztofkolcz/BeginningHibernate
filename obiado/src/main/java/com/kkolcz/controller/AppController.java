package com.kkolcz.controller;

import java.util.List;
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

@Controller
@RequestMapping("/")
public class AppController{

  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
  public String homePage(ModelMap model){
    model.addAttribute("greeting","siema");
    return "home";
  }


}

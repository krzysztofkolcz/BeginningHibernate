package com.kkolcz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String welcomeName(@PathVariable String name, ModelMap model) {
      model.addAttribute("message", "Welcome " + name);
      return "index";
  }

}

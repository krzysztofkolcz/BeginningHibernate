package com.kkolcz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kkolcz.rest.Service;
import com.kkolcz.types.UserData;

@Controller
public class BaseController {

  private static int counter = 0;
  public static final String VIEW_INDEX = "index";
  private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

  @Autowired
  public Service service; 

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome(ModelMap model) {

    model.addAttribute("message", "Welcome");
    model.addAttribute("counter", ++counter);
    logger.debug("[welcome] counter : {}", counter);

    // Spring uses InternalResourceViewResolver and return back index.jsp
    return VIEW_INDEX;

  }

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  public String welcomeName(@PathVariable String name, ModelMap model) {

    model.addAttribute("message", "Welcome " + name);
    logger.debug("[welcomeName] name : {}", name);
    model.addAttribute("counter", ++counter);
    logger.debug("[welcomeName] counter : {}", counter);
    return VIEW_INDEX;

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String welcomeNameRest(@PathVariable String id, ModelMap model) {
    UserData userData = service.getUserData(id);
    model.addAttribute("message", "Welcome " + userData.getName() + "; Age: " + userData.getAge() + "; Salary: " + userData.getSalary() );
    return VIEW_INDEX;

  }


}

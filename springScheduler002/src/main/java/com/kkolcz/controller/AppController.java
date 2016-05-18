package com.kkolcz.controller;
 
import java.util.List;
import java.util.Locale;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class AppController {

    protected static Logger logger = LogManager.getLogger(AppController.class);
 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = { "/blabla" }, method = RequestMethod.GET)
    public String blabla() {
        logger.info("blabla request");
        logger.error("blabla request");
        logger.debug("blabla request debug");
        return "blabla";
    }

}

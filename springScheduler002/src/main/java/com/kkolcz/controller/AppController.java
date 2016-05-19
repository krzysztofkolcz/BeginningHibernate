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

import com.kkolcz.scheduling.TicketRegister;
 
@Controller
@RequestMapping("/")
public class AppController {

    protected static Logger logger = LogManager.getLogger(AppController.class);
    @Autowired TicketRegister ticketRegister;
 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = { "/blabla" }, method = RequestMethod.GET)
    public String blabla() {
        logger.error("blabla request");
        return "blabla";
    }

    @RequestMapping(value = { "/setstatus/{ticketId}/{status}" }, method = RequestMethod.GET)
    public String setStatus(@PathVariable String ticketId,@PathVariable String status) {
        logger.error("set status, ticketid:" + ticketId + "; status:" + status);
        Long ticketIdL = Long.parseLong(ticketId);
        ticketRegister.setStatus(ticketIdL,status);
        return "index";
    }

}

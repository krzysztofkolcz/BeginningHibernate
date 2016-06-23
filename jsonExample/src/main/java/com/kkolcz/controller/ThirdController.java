package com.kkolcz.controller;

import com.kkolcz.model.Greeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class ThirdController{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/third", method = RequestMethod.GET)
    @ResponseBody
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value = "/thirdstring", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String stringing(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }
}

package com.kkolcz.scheduling;
 
import org.springframework.stereotype.Component;
 
@Component("myBean")
public class MyBean {
 
    public void printMessage() {
        System.out.println("MyBean");
    }
}

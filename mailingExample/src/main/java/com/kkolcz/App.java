package com.kkolcz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext( "springConfig.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();
        MyMail myMail = (MyMail)context.getBean("myMail");
        myMail.sendSimpleEmail("kkolcz@power.com.pl");
    }
}

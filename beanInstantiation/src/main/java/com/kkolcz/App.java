package com.kkolcz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.kkolcz.beans.DomainService;
import com.kkolcz.beans.Proxy;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");

        Proxy proxy = (Proxy) context.getBean("proxy");
        System.out.println("App - proxy.pringLogin()");
        proxy.printLogin();
        System.out.println();

        System.out.println("App proxy2.pringLogin()");
        Proxy proxy2 = (Proxy) context.getBean("proxy2");
        proxy2.printLogin();
        System.out.println();

        System.out.println("App domainService.pringLogin()");
        DomainService domainService = (DomainService) context.getBean("domainService");
        domainService.printLogin();

        System.out.println("App domainService2.pringLogin()");
        DomainService domainService2 = (DomainService) context.getBean("domainService2");
        domainService2.printLogin();
        System.out.println();
    }
}

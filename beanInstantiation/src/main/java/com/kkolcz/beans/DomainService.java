package com.kkolcz.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service; 

@Service
public class DomainService{

  @Autowired private Proxy proxy;
  @Value("${prefix}") private String prefix;

  public void printLogin(){
    System.out.println("DomainService - prefix in pringLogin():"+prefix);
    proxy.printLogin();
  }

  public void setProxy(Proxy proxy){
    this.proxy = proxy;
  }

}

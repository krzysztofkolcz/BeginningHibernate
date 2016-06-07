package com.kkolcz.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service; 

@Service
public class Proxy{

  private String loginData;
  @Value("${prefix}") private String prefix;

  public Proxy(){
    System.out.println("Proxy - constructor");
    System.out.println("Proxy - prefix:"+prefix);
    this.loginData = "Proxy - default proxy data";
  }

  public void setPrefix(String prefix){
    this.prefix = prefix;
  }

  public void printLogin(){
    System.out.println("Proxy - printLogin()");
    System.out.println("Proxy - prefix in printLogin():"+prefix);
    System.out.println("Proxy - loginData in printLogin():"+loginData);
  }

  public void setLoginData(String loginData){
    this.loginData = loginData;
  }

}

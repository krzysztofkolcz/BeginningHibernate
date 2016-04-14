package com.kkolcz.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.*;

@Component("ScheduledDailyImplInfiLoop")
public class ScheduledDailyImplInfiLoop implements ScheduledDaily
{

    public void run() {
      System.out.println("ScheduledDailyImplInfiLoop");
      System.out.println("entering infinite loop");
      int i = 0;
      while(true){
          i++;
          System.out.println(i);
          try {
              // thread to sleep for 1000 milliseconds
              Thread.sleep(1000);
          } catch (Exception e) {
              System.out.println(e);
          }

      }
    }
}

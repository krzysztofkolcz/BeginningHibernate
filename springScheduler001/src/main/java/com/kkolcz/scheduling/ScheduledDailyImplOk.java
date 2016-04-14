package com.kkolcz.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("scheduledDailyImplOk")
public class ScheduledDailyImplOk implements ScheduledDaily
{

    public void run() {
      System.out.println("scheduledDailyImplOk");
    }
}

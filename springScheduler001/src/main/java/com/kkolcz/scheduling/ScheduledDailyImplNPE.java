package com.kkolcz.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("scheduledDailyImplNPE")
public class ScheduledDailyImplNPE implements ScheduledDaily
{
    public void run() {
      System.out.println("scheduledDailyImplNPE");
      /* throw new NullPointerException("null pointer exception"); */
    }
}

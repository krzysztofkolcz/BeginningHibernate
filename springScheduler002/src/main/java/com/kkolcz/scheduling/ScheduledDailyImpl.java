package com.kkolcz.scheduling;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.*;

@Component("ScheduledDailyImpl")
public class ScheduledDailyImpl implements ScheduledDaily {

    protected static Logger logger = LogManager.getLogger(ScheduledDailyImpl.class);

    @Autowired TicketRegister ticketRegister;

    public void run() {
      logger.error("ScheduledDailyImpl");
      System.out.println("ScheduledDailyImpl");
      //TODO - tu pętla for dla kilku ticketów
      Long ticketId = ticketRegister.getNextId();
      TicketRegisterAction ticketRegisterAction = new TicketRegisterAction();
      ticketRegister.register(ticketId, ticketRegisterAction );
      logger.error("ticket register; ticketId="+ticketId);
      String status = ticketRegister.getStatus(ticketId);
      logger.error("ticket change status: status="+status);
    }
}

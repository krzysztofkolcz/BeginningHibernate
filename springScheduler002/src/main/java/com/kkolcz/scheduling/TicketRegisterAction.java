package com.kkolcz.scheduling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketRegisterAction {

  private Object action;
  private String status ;

  protected static Logger logger = LogManager.getLogger(TicketRegisterAction.class);

  /* public void startObjectWaiting(){ */
  /*   action.wait(); */
  /* } */
  /*  */
  /* public void stopObjectWaiting(){ */
  /*   action.notifyAll(); */
  /* } */

  public TicketRegisterAction(){
    logger.error("ticket register action constructor");
    this.action = new Object();
    this.status = null;
  }

  public String getStatus(){
    try {
        synchronized(action) {
            while (status == null) {
                action.wait();
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
    }

    return status;
  }

  public void setStatus(String status){
    synchronized(action) {
        this.status = status;
        action.notifyAll();
    }
  }
}

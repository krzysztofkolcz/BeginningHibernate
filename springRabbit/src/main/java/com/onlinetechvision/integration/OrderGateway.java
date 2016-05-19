package com.onlinetechvision.integration;
import java.util.List;

import org.springframework.messaging.Message;

import com.onlinetechvision.model.Order;


public interface OrderGateway {

  /**
     * Processes Order Request
     *
     * @param message SI Message covering Order payload.
     */
  void processOrderRequest(Message<List<Order>> message);
}

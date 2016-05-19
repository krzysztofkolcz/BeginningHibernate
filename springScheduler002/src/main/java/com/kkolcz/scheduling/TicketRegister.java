package com.kkolcz.scheduling;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TicketRegister {

    private Map<Long,TicketRegisterAction> actions = new ConcurrentHashMap<>();
    private Long sequence = 1L;

    public synchronized Long getNextId(){
        sequence++;
        return sequence;
    }

    public void register(Long ticketId, TicketRegisterAction action) {
        actions.put(ticketId, action);
    }

    public void unregister(Long ticketId) {
        actions.remove(ticketId);
    }

    public void setStatus(Long ticketId, String status) {
        TicketRegisterAction ticketRegisterAction = actions.get(ticketId);
        if (ticketRegisterAction != null) {
            actions.get(ticketId).setStatus(status);
        }
    }

    public String getStatus(Long ticketId) {
        TicketRegisterAction ticketRegisterAction = actions.get(ticketId);
        if (ticketRegisterAction== null) {
            return null;
        }
        return ticketRegisterAction.getStatus();
    }

}

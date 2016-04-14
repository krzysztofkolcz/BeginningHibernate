package com.kkolcz.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PollWatcher {

    @Scheduled(cron = "*/5 * * * * *")
    public void getNextMessageAndConfirm() {
        System.out.println("PollWatcher");
    }
}


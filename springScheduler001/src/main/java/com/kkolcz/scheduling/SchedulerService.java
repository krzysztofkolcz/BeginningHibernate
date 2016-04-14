package com.kkolcz.scheduling;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Scheduler for handling jobs
 */
@Service
public class SchedulerService {

    protected static Logger logger = LogManager.getLogger(SchedulerService.class);

    @Autowired
    private ApplicationContext appCtx;

    @Scheduled(cron = "*/5 * * * * *")
    public void doScheduleDaily() {
        logger.info("Start schedule");

        Map<String, ScheduledDaily> scheduled = appCtx.getBeansOfType(ScheduledDaily.class);

        for (Map.Entry<String, ScheduledDaily> entry : scheduled.entrySet() ) {
            logger.info("Running " + entry.getKey());
            entry.getValue().run();
        }

        logger.info("End schedule");
    }
}


package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Запуск планировщика заданий.
 *
 * @author Ilya Aslamov.
 * @version $Id$
 * @since 0.1
 */
public class MainScheduler {
    private Config parserConfig = new Config();
    private String cronExpression = parserConfig.get("cron.time");

    private final static Logger LOGGER = LogManager.getLogger(MainScheduler.class);

    public void startScheduler() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(SchedulerJob.class).build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(String[] args) {
        MainScheduler mainScheduler = new MainScheduler();
        try {
            mainScheduler.startScheduler();
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

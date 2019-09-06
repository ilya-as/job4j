package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.parser.scheduler.SchedulerJob;
import ru.job4j.parser.utils.Config;

/**
 * Запуск планировщика заданий.
 *
 * @author Ilya Aslamov.
 * @version $Id$
 * @since 0.1
 */
public class MainScheduler {
    private final static Logger LOGGER = LogManager.getLogger(MainScheduler.class);
    private Config parserConfig = new Config();
    private String cronExpression = parserConfig.get("cron.time");

    public void startScheduler() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(SchedulerJob.class).build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
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

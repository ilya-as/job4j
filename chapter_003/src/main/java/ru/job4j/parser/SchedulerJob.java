package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Определяет задания для планировщика.
 *
 * @author Ilya Aslamov.
 * @version $Id$
 * @since 0.1
 */
public class SchedulerJob implements Job {
    /**
     * Логирование.
     */
    private final static Logger LOG = LogManager.getLogger(SchedulerJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        HTMLParser htmlParser = new HTMLParser(config, storeSQL.getEndingDate());
        try {
            storeSQL.addVacancy(htmlParser.getDataFromURL());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

package ru.job4j.parser.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import ru.job4j.parser.Parser;
import ru.job4j.parser.utils.Config;
import ru.job4j.parser.db.StoreSQL;
import ru.job4j.parser.parsers.ParserSqlRu;

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
        Parser parserSqlRu = new ParserSqlRu(config, storeSQL.getEndingDate());
        try {
            storeSQL.addElement(parserSqlRu.getDataFromURL());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

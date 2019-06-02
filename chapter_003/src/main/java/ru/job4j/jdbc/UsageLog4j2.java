package ru.job4j.jdbc;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UsageLog4j2 {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    public static void main(String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("trace message {}", version);
        LOG.info("trace message {}", version);
        LOG.warn("trace message {}", version);
        LOG.error("trace message {}", version);
    }
}

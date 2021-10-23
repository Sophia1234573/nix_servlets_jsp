package com.solutions.denisovich.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit  implements ServletContextListener {
    private static final Logger LOG = LogManager.getLogger(AppInit.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Init database connection manager");
        DatabaseConnectionManager.init();
        LOG.info("Database init finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

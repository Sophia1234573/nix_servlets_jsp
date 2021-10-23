package com.solutions.denisovich.config;

import com.solutions.denisovich.utils.PropertiesConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class DatabaseConnectionManager {
    private static final Logger LOG = LogManager.getLogger(DatabaseConnectionManager.class);
    private static HikariDataSource dataSource;

    private DatabaseConnectionManager() {
    }

    public static void init() {
        PropertiesConfig propertiesLoader = new PropertiesConfig();
        propertiesLoader.loadPropertiesFile("jdbc.properties");
        initDataSource(propertiesLoader);
    }

    public static synchronized HikariDataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            init();
            return dataSource;
        }
        return dataSource;
    }

    private static void initDataSource(PropertiesConfig propertiesLoader) {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(propertiesLoader.getProperty("url"));
            config.setUsername(propertiesLoader.getProperty("username"));
            config.setPassword(propertiesLoader.getProperty("password"));
            config.setMaximumPoolSize(10);
            config.setIdleTimeout(10_000);
            config.setConnectionTimeout(10_000);
            config.setDriverClassName(propertiesLoader.getProperty("jdbc.driver"));
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

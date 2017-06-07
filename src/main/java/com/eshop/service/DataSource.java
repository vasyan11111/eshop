package com.eshop.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.String.format;

public class DataSource {



    private static volatile DataSource instance;

    private static final String URL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM '%s'\\;RUNSCRIPT FROM '%s'";
    private ComboPooledDataSource pooledDataSource;

    private DataSource() throws Exception {

        // Disabling C3P0 logs
        Properties p = new Properties(System.getProperties());
        p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");
        System.setProperties(p);

        pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setDriverClass("org.h2.Driver");
        pooledDataSource.setJdbcUrl(prepareUrl());
        pooledDataSource.setUser("sa");
        pooledDataSource.setPassword("");
        pooledDataSource.setMinPoolSize(1);
        pooledDataSource.setAcquireIncrement(1);
        pooledDataSource.setMaxPoolSize(5);
        pooledDataSource.setMaxStatements(180);
    }

    private String prepareUrl() throws URISyntaxException {
            return format(URL, prepareScript("sql/create.sql"), prepareScript("sql/populate.sql"));
    }

    private String prepareScript(String fileName) throws URISyntaxException {
        return Paths.get(getClass().getClassLoader().getResource(fileName).toURI()).toAbsolutePath().toString();
    }

    public static DataSource getInstance() throws Exception {
        if (instance == null) {
            synchronized (DataSource.class){
                if (instance == null)
                    instance = new DataSource();
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return this.pooledDataSource.getConnection();
    }
}

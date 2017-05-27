package com.eshop.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.String.format;

public class DataSource {

    private static final String URL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM '%s'\\;RUNSCRIPT FROM '%s'";
    private static DataSource datasource;
    private ComboPooledDataSource cpds;

    private DataSource() throws Exception {

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.h2.Driver");
        cpds.setJdbcUrl(prepareUrl());
        cpds.setUser("sa");
        cpds.setPassword("");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    private String prepareUrl() throws URISyntaxException {
        return format(URL, prepareScript("sql/create.sql"), prepareScript("sql/populate.sql"));
    }

    private String prepareScript(String fileName) throws URISyntaxException {
        return Paths.get(getSystemResource(fileName).toURI()).toAbsolutePath().toString();
    }

    public static DataSource getInstance() throws Exception {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}

package com.eshop.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.String.format;

public class DataSource {

    private static volatile DataSource instance;

    private static final String URL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM '%s'\\;RUNSCRIPT FROM '%s'";
    private ComboPooledDataSource cpds;

    private DataSource() throws Exception {

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.h2.Driver");
        cpds.setJdbcUrl("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM '~/ideaprojects/eshop-denis/eshop/src/main/resources/sql/create.sql'\\;RUNSCRIPT FROM '~/ideaprojects/eshop-denis/eshop/src/main/resources/sql/populate.sql'");//prepareUrl()
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
        if (instance == null) {
            synchronized (DataSource.class){
                if (instance == null)
                    instance = new DataSource();
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}

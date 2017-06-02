package com.eshop.dao;

import com.eshop.service.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<E, K> {
    protected Connection connection;

    public AbstractDAO() throws Exception {
        DataSource connectionPool = DataSource.getInstance();
        connection = connectionPool.getConnection();
    }

    public abstract List<E> getAll();
    public abstract E update(E entity);
    public abstract E findEntity(K id);
    public abstract boolean delete(K id);
    public abstract boolean addNew(E entity);
}
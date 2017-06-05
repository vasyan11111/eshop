package com.eshop.dao;

import com.eshop.service.DataSource;

import java.sql.Connection;

public abstract class AbstractDAO<E, K> {

    public abstract E update(E entity);
    public abstract E findEntity(K id);
    public abstract boolean delete(K id);
    public abstract boolean addNew(E entity);

    protected Connection getConnection() {
        try {
            return DataSource.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException("something wrong!");
        }
    }
}
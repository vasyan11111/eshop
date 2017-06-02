package com.eshop.dao;

import com.eshop.dao.entities.Mobile;

import java.sql.Connection;
import java.util.List;

public abstract class MobilesDAO implements AutoCloseable {

    protected Connection connection;

    public abstract Mobile find(String series);

    public abstract List<Mobile> findAll();

    public abstract void update(Mobile mobile);

    public abstract void delete(String series);

    public abstract void sell(Mobile mobile, int boughtItemsAmount);

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

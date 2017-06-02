package com.eshop.dao;

import com.eshop.dao.entities.Laptop;

import java.sql.Connection;
import java.util.List;

public abstract class LaptopsDAO extends AbstractDAO<Laptop, String> implements AutoCloseable{

    public LaptopsDAO() throws Exception {
    }

    public abstract Laptop findEntity(String series);

    public abstract boolean addNew(Laptop laptop);

    public abstract List<Laptop> getAll();

    public abstract Laptop update(Laptop laptop);

    public abstract boolean delete(String series);

    public abstract void sell(Laptop laptop, int boughtItemsAmount);

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

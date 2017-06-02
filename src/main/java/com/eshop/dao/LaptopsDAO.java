package com.eshop.dao;

import com.eshop.dao.entities.Laptop;

import java.sql.Connection;
import java.util.List;

public abstract class LaptopsDAO implements AutoCloseable{
    protected Connection connection;

    public abstract Laptop find(String series);

    public abstract List<Laptop> findAll();

    public abstract void update(Laptop laptop);

    public abstract void delete(String series);

    public abstract void sell(Laptop laptop, int boughtItemsAmount);

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

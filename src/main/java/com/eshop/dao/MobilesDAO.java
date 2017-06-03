package com.eshop.dao;

import com.eshop.dao.entities.Product;

import java.util.List;

public abstract class MobilesDAO extends AbstractDAO<Product, String> {

    public MobilesDAO() {
    }

    public abstract Product findEntity(String series);

    public abstract boolean addNew(Product product);

    public abstract List<Product> getAll();

    public abstract Product update(Product product);

    public abstract boolean delete(String series);

    public abstract void sell(Product product, int boughtItemsAmount);
}

package com.eshop.dao;

import com.eshop.dao.entities.Product;

import java.util.List;

public abstract class ProductDAO extends AbstractDAO<Product, String> {

    public ProductDAO() {
    }

    public abstract Product findEntity(String series);

    public abstract boolean addNew(Product product);

    public abstract List<Product> findAll();

    public abstract Product update(Product product);

    public abstract boolean delete(String series);

    public abstract List<Product> findSpecifiedProduct(String productType);

    public abstract void sell(Product product, int boughtItemsAmount);
}

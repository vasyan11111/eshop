package com.eshop.dao;

import com.eshop.dao.entities.Mobile;

import java.util.List;

public abstract class MobilesDAO extends AbstractDAO<Mobile, String> {

    public MobilesDAO() throws Exception {
    }

    public abstract Mobile findEntity(String series);

    public abstract boolean addNew(Mobile mobile);

    public abstract List<Mobile> getAll();

    public abstract Mobile update(Mobile mobile);

    public abstract boolean delete(String series);

    public abstract void sell(Mobile mobile, int boughtItemsAmount);
}

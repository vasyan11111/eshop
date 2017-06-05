package com.eshop.dao.jdbc;

import com.eshop.dao.AbstractDAO;
import com.eshop.dao.entities.OrderEntry;

public class JDBCOrderEntryDAO extends AbstractDAO<OrderEntry, String> {
    @Override
    public OrderEntry update(OrderEntry entity) {
        return null;
    }

    @Override
    public OrderEntry findEntity(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean addNew(OrderEntry entity) {
        return false;
    }
}

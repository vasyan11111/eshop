package com.eshop.dao.entities;

import java.util.List;

public class Order {

    private Integer id;
    private Double totalPrice;
    private User user;

    private List<OrderEntry> entries;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderEntry> entries) {
        this.entries = entries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.eshop.dao.entities;

public class Mobile {
    private final Integer id;
    private final String company;
    private final String model;
    private final String series;
    private final Integer price;
    private Integer amount;
    private final String color;

    public Mobile(Integer id, String company, String model, String series, Integer price, Integer amount, String color) {
        this.id = id;
        this.company = company;
        this.model = model;
        this.series = series;
        this.price = price;
        this.amount = amount;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public String getSeries() {
        return series;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }
}

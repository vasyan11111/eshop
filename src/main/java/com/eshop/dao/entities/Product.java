package com.eshop.dao.entities;

public class Product {
    private final Integer id;
    private final String company;
    private final String model;
    private final String series;
    private final Double price;
    private Integer stock;
    private final String productType;


    public Product(Integer id, String company, String model, String series, Double price, Integer stock, String productType) {
        this.id = id;
        this.company = company;
        this.model = model;
        this.series = series;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
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

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public String toString() {
        return company  +
                ", " + model +
                ", " + series +
                ", price = " + price;
    }
}

package com.eshop.dao.entities;

public class Product {
    private final Integer id;
    private final String company;
    private final String model;
    private final String series;
    private final Integer price;
    private Integer stock;
    private final String productType;

    //TODO: Обработать ввод отрицательных значений
    public Product(Integer id, String company, String model, String series, Integer price, Integer stock, String productType) {
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

    public Integer getPrice() {
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
        return "Product{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", series='" + series + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", productType='" + productType + '\'' +
                '}';
    }
}

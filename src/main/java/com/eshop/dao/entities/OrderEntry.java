package com.eshop.dao.entities;

public class OrderEntry {

    private Product product;
    private Double price;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderEntry{" +
                "product=" + product +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

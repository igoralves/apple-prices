package com.igor.service;

import java.util.Currency;

public class Product {

    private String model;
    private Float price;
    private Currency currency;

    public Product(String model, Float price, Currency currency) {
        this.model = model;
        this.price = price;
        this.currency = currency;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

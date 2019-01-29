package com.marry.productsDeal.entities;

public class Product {
    public static final int DEF_DSCNT = 10;
    private String title;
    private double price;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Product() {
    }

    public double getCost(int quantity) {
        double realCost = quantity * price;
        return realCost - realCost * calcDiscount(quantity) / 100;
    }

    protected int calcDiscount(int quantity) {
        if (quantity > 10) {
            return DEF_DSCNT;
        } else {
            return 0;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}




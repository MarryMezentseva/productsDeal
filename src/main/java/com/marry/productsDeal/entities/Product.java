package com.marry.productsDeal.entities;

public class Product {
    public static final int DEF_DSCNT = 10;
    private String title;
    private Double price;


    public Product(String title, Double price) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}




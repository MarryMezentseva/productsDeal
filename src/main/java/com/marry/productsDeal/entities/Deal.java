package com.marry.productsDeal.entities;

        import java.util.*;

public class Deal {

    private Date date = new Date();
    private User seller;
    private User buyer;
    private final Map<Product, Integer> products = new HashMap<>();

    public Deal(User seller, User buyer) {
        this.seller = seller;
        this.buyer = buyer;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double getSum() {

        double rez = 0;
        Set<Map.Entry<Product, Integer>> set = products.entrySet();
        for (Map.Entry<Product, Integer> entry : set) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            rez += product.getCost(quantity);
        }
        return rez;
    }

}

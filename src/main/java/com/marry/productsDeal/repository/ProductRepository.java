package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;

import java.util.*;

public class ProductRepository {
    private List<Product> productList = new LinkedList<>();

    public void createBase() {
        Product product1 = new Product("nut", 450.90);
        Product product2 = new Product("orange", 253.50);
        Product product3 = new Product("apple", 9.90);
        Product product4 = new Product("beans", 59.0);
        Product product5 = new Product("nut", 280.15);
        Product product6 = new Product("soy", 185.55);
        Product product7 = new Product("orange", 39.90);
        Product product8 = new Product("orange", 39.00);
        Product product9 = new Product("orange", 43.50);
        Product product10 = new Product("nut", 290.85);
        Product product11 = new Product("orange", 45.00);
        Product product12 = new Product("apple", 10.89);
        Product product13 = new Product("soy", 200.90);
        Product product14 = new Product("orange", 39.49);
        Product product15 = new Product("apple", 15.00);
        Product product16 = new Product("apple", 8.50);
        Product product17 = new Product("soy", 179.99);
        Product product18 = new Product("orange", 40.00);
        Product product19 = new Product("apple", 4.99);
        Product product20 = new Product("nut", 450.00);


        productList.addAll(Arrays.asList(product1, product2, product3, product4, product5, product6,
                product7, product8, product9, product10, product11, product12, product13, product14,
                product15, product16, product17, product18, product19, product20));
    }

    public List<Product> findAll() {
               return productList;
    }

    public Product findOneFromBase(String name) throws NonExistingProductException {

        for (Product product : productList) {
            String result = product.getTitle();
            if (name.equals(result)) {
                return product;
            }
        }
        throw new NonExistingProductException("product with name: " + name + " not found!");
    }

    public Product findOneFromBase(double price) throws NonExistingProductException {

        for (Product product : productList) {
            double result = product.getPrice();
            if (price == result) {//what is gong on here?
                return product;
            }
        }
        throw new NonExistingProductException("product with price: " + price + " not found!");
    }

    public List<Product> findByNamesAndSort(String... names) {
        List<Product> sortedList = new ArrayList<>();
        for (Product product : productList) {
            String foundName = product.getTitle();
            for (String n : names) {
                if (n.equals(foundName)) {
                    sortedList.add(product);
                }

            }
        }
        sortedList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return sortedList;
    }

    public List<Product> findByPriceRange(double startPrice, double endPrice) {
        List<Product> listByPriceRange = new ArrayList<>();
        for (Product product : productList) {
            double price = product.getPrice();
            if ((price <= endPrice) && (price >= startPrice)) {//what is gong on here? Get rid of this loop
                listByPriceRange.add(product);
            }
        }
        return listByPriceRange;
    }
}

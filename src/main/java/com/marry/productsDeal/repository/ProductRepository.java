package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;
import com.marry.productsDeal.utils.CsvProductReader;
import com.marry.productsDeal.utils.JsonProductReader;
import com.marry.productsDeal.utils.XmlProductReader;

import java.io.IOException;
import java.util.*;

public class ProductRepository {

    private List<Product> productList;

    public ProductRepository() {
        this.productList = createBase();
    }

//    public List<Product> createBase() {
//        CsvProductReader csvProductReader = new CsvProductReader("productList.csv");
//        List<Product> productList = csvProductReader.read();
//        return productList;
//    }

//    public List<Product> createBase() {
//        XmlProductReader xmlProductReader = new XmlProductReader("productList.xml");
//        List<Product> productList = xmlProductReader.read();
//        return productList;
//    }

    public List<Product> createBase()  {
        JsonProductReader jsonProductReader = new JsonProductReader("productList.json");
        List<Product> productList = jsonProductReader.read();
        return productList;
    }

    public Product create(Product product) {
        productList.add(product);
        return product;
    }

    public List<Product> findAll() {
        return productList;
    }

    public void deleteAll() {
        productList.clear();
    }

    public Product findByName(String name) throws NonExistingProductException {

        for (Product product : productList) {
            String result = product.getTitle();
            if (name.equals(result)) {
                return product;
            }
        }
        throw new NonExistingProductException("product with name: " + name + " not found!");
    }

    public Product findByPrice(double price) throws NonExistingProductException {

        for (Product product : productList) {
            double result = product.getPrice();//inline it
            if (price == result) {
                return product;
            }
        }
        throw new NonExistingProductException("product with price: " + price + " not found!");
    }


    public List<Product> sortByPriceRange(double startPrice, double endPrice) {
        List<Product> listByPriceRange = new ArrayList<>();
        for (Product product : productList) {
            double price = product.getPrice();
            if ((price <= endPrice) && (price >= startPrice)) {
                listByPriceRange.add(product);
            }
        }
        return listByPriceRange;
    }

    private List<Product> findByNames(String... names) {
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            String foundName = product.getTitle();
            for (String n : names) {
                if (n.equals(foundName)) {
                    products.add(product);
                }
            }
        }
        return products;
    }

    public List<Product> findByNamesAndSort(String... names) {//to do contain
        List<Product> products = findByNames(names);

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return products;
    }

    public List<Product> findByNamesAndSortByNamesAndPrice(String... names) {
        List<Product> products = findByNames(names);
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int result = o1.getTitle().compareTo(o2.getTitle());
                if (result == 0) {
                    return Double.compare(o1.getPrice(), o2.getPrice());
                } else {
                    return result;
                }
            }
        });
        return products;
    }

    public Product findByMaxPrice() {
        Product productMaxPrice = null;
        for (Product product : productList) {
            if (productMaxPrice == null) {
                productMaxPrice = product;
            }
            if (product.getPrice() > productMaxPrice.getPrice()) {
                productMaxPrice = product;
            }
        }
        return productMaxPrice;
    }

    public Product findByMinPrice() {
        Product productMinPrice = null;
        for (Product product : productList) {
            if (productMinPrice == null) {
                productMinPrice = product;
            }
            if (product.getPrice() < productMinPrice.getPrice()) {
                productMinPrice = product;
            }
        }
        return productMinPrice;
    }

    public List<Product> find(Product example) {
        String title = example.getTitle();
        Double price = example.getPrice();
        if (title == null && (price == null)){
            throw new IllegalArgumentException("At least one parameter should be not null");
        }

        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            if (title != null && (!title.equals(product.getTitle()))) {
                continue;
            }

            if (price != null && (!price.equals(product.getPrice()))) {
                continue;
            }

            products.add(product);
        }
        return products;
    }

}

package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;
import com.marry.productsDeal.utils.ProductsReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This class allows to use the list of products, which is read from file
 */
public class ProductRepository {

    private List<Product> productList;

    public ProductRepository(ProductsReader reader) {
        this.productList = reader.read();
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
            if (price == product.getPrice()) {
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
        List<String> listNames = Arrays.asList(names);
        for (Product product : productList) {
            String foundName = product.getTitle();
            if(listNames.contains(foundName)){
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> findByNamesAndSort(String... names) {
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
        if (title == null && (price == null)) {
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

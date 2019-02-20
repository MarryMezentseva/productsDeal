package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;

import java.util.*;

//         1) создать метод  public Product  create(Product p) он просто добавляет в кол. продукт и возвращает его же.
// После этого все создаия продуктов делать через него.
//        2) создать метод такой же как с сортировкой по имени, но реализовать его еще таким образом,
// чтобы он сортировал по имени (как и было), но еще и по цене.
// Те когда встречаются одиннаковые имена продуктов, для компаратора они равны =>
// он их расположит как попало. Так вот нужно в этом случае досортировать по цене.
// Подсказка: нужно чуть подправить существующий компаратор.
//        3) создать метод кот найдет самый дорогой продукт
//        4)создать метод кот найдет самый дешевый продукт
//        5) написать на это все тесты
public class ProductRepository {

    private List<Product> productList = new LinkedList<>();

    public ProductRepository() {
        createBase();
    }

    public List<Product> createBase() {
        create(new Product("nut", 450.90));
        create(new Product("orange", 253.50));
        create(new Product("apple", 9.90));
        create(new Product("beans", 59.0));
        create(new Product("nut", 280.15));
        create(new Product("soy", 185.55));
        create(new Product("orange", 39.90));
        create(new Product("orange", 39.00));
        create(new Product("orange", 43.50));
        create(new Product("nut", 290.85));
        create(new Product("orange", 45.00));
        create(new Product("apple", 10.89));
        create(new Product("soy", 200.90));
        create(new Product("orange", 39.49));
        create(new Product("apple", 15.00));
        create(new Product("apple", 8.50));
        create(new Product("soy", 179.99));
        create(new Product("orange", 40.00));
        create(new Product("apple", 4.99));
        create(new Product("nut", 450.00));
        return productList;
    }

    public Product create(Product product) {
        productList.add(product);
        return product;
    }

    public List<Product> findAll() {
        return productList;
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
            double result = product.getPrice();
            if (price == result) {
                return product;
            }
        }
        throw new NonExistingProductException("product with price: " + price + " not found!");
    }

    public List<Product> findByNamesAndSort(String... names) {//to do contains
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

    public List<Product> sortByNameAndPrice(String... names) {
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
                int result = o1.getTitle().compareTo(o2.getTitle());
                if (result == 0) {
                    return Double.compare(o1.getPrice(), o2.getPrice());
                } else {
                    return result;
                }
            }
        });
        return sortedList;
    }

    Product findByMaxPrice() {
        Product productMaxPrice = new Product();
        productMaxPrice.setPrice(Double.MIN_VALUE);
        for (Product product : productList) {
            if (product.getPrice() > productMaxPrice.getPrice()){
                productMaxPrice = product;
            }
        }
        return productMaxPrice;
    }

    Product findByMinPrice() {
        Product productMinPrice = new Product();
        productMinPrice.setPrice(Double.MAX_VALUE);
        for (Product product : productList) {
            if (product.getPrice() < productMinPrice.getPrice()){
                productMinPrice = product;
            }
        }
        return productMinPrice;
    }
}

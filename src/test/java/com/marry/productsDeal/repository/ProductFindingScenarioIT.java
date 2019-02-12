package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ProductFindingScenarioIT {
    ProductRepository productRepository = new ProductRepository();

    @BeforeClass
    public void init() {
        productRepository.createBase();
    }

    @Test
    public void testFindAll() {
        assertEquals(productRepository.findAll().size(), 20);
    }

    @Test
    public void testFindOneByName() {
        assertNotNull(productRepository.findOneFromBase("nut"));
    }

    @Test
    public void testFindOneByPrice() {
        assertNotNull(productRepository.findOneFromBase(15.00));
    }

    @Test
    public void testFindByNamesAndSort() {
       final String NAME_1 = "orange";
       final String NAME_2 = "apple";
     List<Product> productList = productRepository.findByNamesAndSort(NAME_1,NAME_2);
        for (Product product:productList) {
            String name = product.getTitle();
            boolean result = (name.equals(NAME_1)) || (name.equals(NAME_2));
            assertTrue(result);
        }
    }

    @Test
    public void testFindByPriceRange() {
        final double START_PRICE = 1.15;
        final double END_PRICE = 10.50;
        List<Product> productList = productRepository.findByPriceRange(START_PRICE, END_PRICE);
        for (Product product : productList) {
            double price = product.getPrice();
            boolean result = (price <= END_PRICE) && (price >= START_PRICE);
            assertTrue(result);
        }
    }

    @Test(expectedExceptions = NonExistingProductException.class)
    public void negativeTestFindOneByName() {
        productRepository.findOneFromBase("coococoo");
    }

    @Test(expectedExceptions = NonExistingProductException.class)
    public void negativeTestFindOneByPrice() {
        productRepository.findOneFromBase(00.00);
    }

}
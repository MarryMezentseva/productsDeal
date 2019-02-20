package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.*;

//format code
public class ProductFindingScenarioIT {
    ProductRepository productRepository;

    @BeforeClass
    public void init() {
        productRepository = new ProductRepository();
    }

    @Test
    public void testFindAll() {
        assertEquals(productRepository.findAll().size(), 20);
    }

    @Test
    public void testFindOneByName() {
        assertNotNull(productRepository.findByName("nut"));
    }

    @Test
    public void testFindOneByPrice() {
        assertNotNull(productRepository.findByPrice(15.00));
    }

    @Test
    public void testFindByNamesAndSort() {
        final String NAME_1 = "apple";
        final String NAME_2 = "orange";
        List<Product> productList = productRepository.findByNamesAndSort(NAME_1, NAME_2);
        for (Product product : productList) {
            assertEquals(productList.size(), 12);
            assertEquals(productList.get(0).getTitle(), NAME_1);
            assertEquals(productList.get(productList.size() - 1).getTitle(), NAME_2);
        }
    }

    @Test
    public void testSortByPriceRange() {
        final double START_PRICE = 200.00;
        final double END_PRICE = 400.00;
        List<Product> productList = productRepository.sortByPriceRange(START_PRICE, END_PRICE);
        for (Product product : productList) {
            double price = product.getPrice();
            boolean result = (price <= END_PRICE) && (price >= START_PRICE);
            assertTrue(result);
        }
    }

    @Test
    public void testSortByNameAndPrice() {
        final String NAME_1 = "nut";
        final String NAME_2 = "soy";
        List<Product> productList = productRepository.sortByNameAndPrice(NAME_1, NAME_2);
        for (Product product : productList) {
            assertEquals(productList.size(), 7);
            assertEquals(productList.get(0).getTitle(), NAME_1);
            boolean result = productList.get(0).getPrice() < productList.get(1).getPrice();
            assertTrue(result);
            assertEquals(productList.get(productList.size() - 1).getTitle(), NAME_2);
            boolean result1 = productList.get(productList.size() - 2).getPrice() <
                    productList.get(productList.size() - 1).getPrice();
            assertTrue(result1);
        }
    }

    @Test
    public void testFindByMaxPrice() {
        assertEquals(productRepository.findByMaxPrice().getPrice(), 450.90);
    }

    @Test
    public void testFindByMinPrice() {
        assertEquals(productRepository.findByMinPrice().getPrice(), 4.99);
    }

    @Test(expectedExceptions = NonExistingProductException.class)
    public void negativeTestFindOneByName() {
        productRepository.findByName("non_existing_product_name");
    }

    @Test(expectedExceptions = NonExistingProductException.class)
    public void negativeTestFindOneByPrice() {
        productRepository.findByPrice(Double.MIN_VALUE);
    }

}
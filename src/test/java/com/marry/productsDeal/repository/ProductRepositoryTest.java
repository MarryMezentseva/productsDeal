package com.marry.productsDeal.repository;

import com.marry.productsDeal.entities.Product;
import com.marry.productsDeal.exceptions.NonExistingProductException;
import com.marry.productsDeal.utils.CsvProductReader;
import com.marry.productsDeal.utils.ProductsReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

public class ProductRepositoryTest {
    ProductRepository productRepository;

    @BeforeMethod
    public void init() {
        ProductsReader reader = new CsvProductReader("productList.csv");
        productRepository = new ProductRepository(reader);
        List<Product> productList = reader.read();
        assertTrue(productList.size() > 1);
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
    public void testFindProductsByNamesAndSort() {
        //given
        final String APPLE_NAME = "apple";
        final String ORANGE_NAME = "orange";
        productRepository.deleteAll();
        productRepository.create(new Product(ORANGE_NAME, 75.0));
        productRepository.create(new Product(APPLE_NAME, 25.0));
        productRepository.create(new Product(ORANGE_NAME, 73.0));
        productRepository.create(new Product(ORANGE_NAME, 77.0));
        productRepository.create(new Product(APPLE_NAME, 15.0));
        productRepository.create(new Product("unknown product1", 737.0));
        productRepository.create(new Product("unknown product2", 153.0));
        //when
        List<Product> productList = productRepository.findByNamesAndSort(APPLE_NAME, ORANGE_NAME);
        //then
        assertEquals(productList.size(), 5);
        assertEquals(productList.get(0).getTitle(), APPLE_NAME);
        assertEquals(productList.get(1).getTitle(), APPLE_NAME);
        assertEquals(productList.get(2).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(3).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(4).getTitle(), ORANGE_NAME);

    }

    @Test
    public void testSortByPriceRange() {
        final double START_PRICE = 200.00;
        final double END_PRICE = 400.00;
        List<Product> productList = productRepository.sortByPriceRange(START_PRICE, END_PRICE);
        for (Product product : productList) {
            double price = product.getPrice();
            assertTrue((price <= END_PRICE) && (price >= START_PRICE));
        }
    }

    @Test
    public void testFindProductsByNamesByPrice() {
        //given
        final String APPLE_NAME = "apple";
        final String ORANGE_NAME = "orange";
        productRepository.deleteAll();
        productRepository.create(new Product(ORANGE_NAME, 75.0));
        productRepository.create(new Product(APPLE_NAME, 25.0));
        productRepository.create(new Product(ORANGE_NAME, 73.0));
        productRepository.create(new Product(ORANGE_NAME, 77.0));
        productRepository.create(new Product(APPLE_NAME, 15.0));
        productRepository.create(new Product(ORANGE_NAME, 75.10));
        productRepository.create(new Product(APPLE_NAME, 25.10));
        productRepository.create(new Product(ORANGE_NAME, 73.10));
        productRepository.create(new Product(ORANGE_NAME, 77.10));
        productRepository.create(new Product(APPLE_NAME, 15.10));
        productRepository.create(new Product("unknown product1", 737.0));
        productRepository.create(new Product("unknown product2", 153.0));
        //when
        List<Product> productList = productRepository.findByNamesAndSortByNamesAndPrice(APPLE_NAME, ORANGE_NAME);
        //then
        assertEquals(productList.size(), 10);

        assertEquals(productList.get(0).getTitle(), APPLE_NAME);
        assertEquals(productList.get(0).getPrice(), 15.0);

        assertEquals(productList.get(1).getTitle(), APPLE_NAME);
        assertEquals(productList.get(1).getPrice(), 15.10);

        assertEquals(productList.get(2).getTitle(), APPLE_NAME);
        assertEquals(productList.get(2).getPrice(), 25.0);

        assertEquals(productList.get(3).getTitle(), APPLE_NAME);
        assertEquals(productList.get(3).getPrice(), 25.10);

        assertEquals(productList.get(4).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(4).getPrice(), 73.0);

        assertEquals(productList.get(5).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(5).getPrice(), 73.10);

        assertEquals(productList.get(6).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(6).getPrice(), 75.0);

        assertEquals(productList.get(7).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(7).getPrice(), 75.10);

        assertEquals(productList.get(8).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(8).getPrice(), 77.0);

        assertEquals(productList.get(9).getTitle(), ORANGE_NAME);
        assertEquals(productList.get(9).getPrice(), 77.1);

    }


    @Test
    public void testFindByMaxPrice() {
        assertEquals(productRepository.findByMaxPrice().getPrice(), 450.90);
        System.out.println();
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

    //********************************************************
    @Test
    public void findProd_name_price() {
        //given
        //-fill up DB
        productRepository.deleteAll();
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));
        productRepository.create(new Product("aaa", 30.0));
        productRepository.create(new Product("bbb", 10.0));
        productRepository.create(new Product("ccc", 10.0));
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));

        //when
        List<Product> list = productRepository.find(new Product("bbb", 20.0));
        //then
        //-verify list
        assertEquals(list.size(), 2);
    }

    @Test
    public void findProd_name() {
        //given
        //-fill up DB
        productRepository.deleteAll();
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));
        productRepository.create(new Product("aaa", 30.0));
        productRepository.create(new Product("bbb", 10.0));
        productRepository.create(new Product("ccc", 10.0));
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));

        //when
        List<Product> list = productRepository.find(new Product("bbb", null));
        //then
        //-verify list
        assertEquals(list.size(), 3);
    }


    @Test
    public void findProd_price() {
        //given
        //-fill up DB
        productRepository.deleteAll();
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));
        productRepository.create(new Product("aaa", 30.0));
        productRepository.create(new Product("bbb", 10.0));
        productRepository.create(new Product("ccc", 10.0));
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));
        //when
        List<Product> list = productRepository.find(new Product(null, 10.0));
        //then
        //-verify list
        assertEquals(list.size(), 4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void negativeFindProd() {
        //given
        //-fill up DB
        productRepository.deleteAll();
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));
        productRepository.create(new Product("aaa", 30.0));
        productRepository.create(new Product("bbb", 10.0));
        productRepository.create(new Product("ccc", 10.0));
        productRepository.create(new Product("aaa", 10.0));
        productRepository.create(new Product("bbb", 20.0));
        productRepository.create(new Product("ccc", 30.0));
        //when
        productRepository.find(new Product(null, null));
        //expect exception
    }
}
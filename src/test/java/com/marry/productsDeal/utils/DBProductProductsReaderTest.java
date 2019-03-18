package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DBProductProductsReaderTest {

    @Test
    public void testRead() {
        DBProductReader dbProductReader = new DBProductReader("connection.properties");
        List<Product> productList = dbProductReader.read();
        assertTrue(productList.size() > 1);
        System.out.println();
    }
}
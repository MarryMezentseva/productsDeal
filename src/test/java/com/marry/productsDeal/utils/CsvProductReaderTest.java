package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CsvProductReaderTest {

    @Test
    public void testRead() {
        CsvProductReader csvProductReader = new CsvProductReader("productList.csv");
        List<Product> productList = csvProductReader.read();
        assertTrue(productList.size()> 1);
    }
}
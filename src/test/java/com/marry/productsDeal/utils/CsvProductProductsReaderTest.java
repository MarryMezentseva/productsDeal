package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CsvProductProductsReaderTest {
private InputStream inputStream;

    @Test
    public void testRead() {
        CsvProductReader csvProductReader = new CsvProductReader("productList.csv");
        List<Product> productList = csvProductReader.read();
        assertTrue(productList.size()> 1);
    }

@Test
    public void testReadInputStream() {
        inputStream = CsvProductReader.class.getResourceAsStream("productList.csv");
         CsvProductReader csvProductReader = new CsvProductReader(inputStream);
         List<Product> productList = csvProductReader.readInputStream();
         assertTrue(productList.size()> 1);
    }
 }

package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DBProductProductsReaderTest {

    @Test
    public void testRead() {
        DBProductReader dbProductReader = new DBProductReader("D:\\marry_programming\\productsDeal\\src\\test\\resources\\com\\marry\\productsDeal\\utils\\productList.properties");//todo trouble with paths
        List<Product> productList = dbProductReader.read();
        assertTrue(productList.size() > 1);
    }
}
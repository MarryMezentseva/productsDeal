package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DBProductReaderTest {

    @Test
    public void testRead() {
        DBProductReader dbProductReader = new DBProductReader();
        List<Product> productList = dbProductReader.read();
        assertTrue(productList.size() > 1);
        System.out.println();
    }
}
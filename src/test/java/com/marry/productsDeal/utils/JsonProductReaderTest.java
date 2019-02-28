package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class JsonProductReaderTest {

    @Test
    public void testReadByJson()  {
        JsonProductReader jsonProductReader = new JsonProductReader("productList.json");
        List<Product> productList = jsonProductReader.read();
        assertTrue(productList.size() > 1);
        System.out.println();
    }
}
package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class JsonProductProductsReaderTest {

    @Test
    public void testReadByJson()  {
        JsonProductReader jsonProductReader = new JsonProductReader("com/marry/productsDeal/utils/productList.json");
        List<Product> productList = jsonProductReader.read();
        assertTrue(productList.size() > 1);
        System.out.println();
    }
}
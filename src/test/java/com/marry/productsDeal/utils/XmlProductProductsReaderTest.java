package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class XmlProductProductsReaderTest {

    @Test
    public void testRead() {
        XmlProductReader xmlProductReader = new XmlProductReader("com/marry/productsDeal/utils/productList.xml");
        List<Product> productList = null;
        try {
            productList = xmlProductReader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(productList.size() > 1);
    }
}
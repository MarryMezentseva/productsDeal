package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class XmlProductReaderTest {

    @Test
    public void testRead() throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        XmlProductReader xmlProductReader = new XmlProductReader("productList.xml");
        List<Product> productList = xmlProductReader.read();
        assertTrue(productList.size() > 1);
        System.out.println();
    }
}
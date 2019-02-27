package com.marry.productsDeal.utils;
import com.marry.productsDeal.entities.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlProductReader {
    private String path;

    public XmlProductReader(String path){
        this.path = path;
    }

    public List<Product> read() throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
        List<Product> productList = new ArrayList<>();

        //Build DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path);

        //Create XPath
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        XPathExpression expr = xpath.compile("//application/products");

        Object productsObject = expr.evaluate(doc, XPathConstants.NODE);
        NodeList nodes = ((Node)productsObject).getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++){
            if (nodes.item(i) instanceof Element){
                Element productElement = (Element)(nodes.item(i));
                String title = productElement.getElementsByTagName("title").item(0).getTextContent();
                double price = Double.parseDouble(productElement.getElementsByTagName("price").item(0).getTextContent());
                Product product = new Product(title, price);
               productList.add(product);
            }
        }

        return productList;
    }
}

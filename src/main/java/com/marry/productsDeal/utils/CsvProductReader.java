package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvProductReader implements ProductsReader {

    private InputStream inputStream;

    public CsvProductReader(String filePath) {
        try {
            this.inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CsvProductReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        String lines = null;
        try {
            lines = IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strings = new String[0];
        if (lines != null) {
            strings = lines.split("\r\n");
        }
        List<String> stringList = Arrays.asList(strings);
        for (int i = 1; i < stringList.size(); i++) {
            String[] res = stringList.get(i).split(",");
            Product product = new Product(res[0], Double.parseDouble(res[1]));
            products.add(product);
        }
        return products;
    }

}

package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvProductReader implements ProductsReader {
    private String filePath;
    private InputStream inputStream;

    public CsvProductReader(String filePath) {
        this.filePath = filePath;
    }

    public CsvProductReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public List<Product> readInputStream() {
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



    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try {
            File file = new File(filePath);
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            for (int i = 1; i < lines.size(); i++) {
                String[] res = lines.get(i).split(",");
                Product product = new Product(res[0], Double.parseDouble(res[1]));
                products.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}

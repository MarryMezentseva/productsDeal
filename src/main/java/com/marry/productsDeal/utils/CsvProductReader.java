package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvProductReader {
    private String path;

    public CsvProductReader(String path) {
        this.path = path;
    }

    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try {

            File f = new File(path);

            List<String> lines = FileUtils.readLines(f, "UTF-8");

            for (int i = 1; i < lines.size(); i++) {
                String[] res = lines.get(i).split(",");
                Product product = new Product(res[0],Double.parseDouble(res[1]));
                products.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}

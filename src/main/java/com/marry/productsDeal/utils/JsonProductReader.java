package com.marry.productsDeal.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.marry.productsDeal.entities.Product;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonProductReader {
    private String path;

    public JsonProductReader(String path) {
        this.path = path;
    }

    public List<Product> read() {
        List<Product> productList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        // Convert JSON string from file to Object

        try {
            productList = mapper.readValue(new File(path),
                    new TypeReference<List<Product>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }

}

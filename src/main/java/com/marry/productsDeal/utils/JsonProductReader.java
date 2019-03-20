package com.marry.productsDeal.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//remove free space
import com.marry.productsDeal.entities.Product;
//remove free space

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonProductReader implements ProductsReader{
    private String path;

    public JsonProductReader(String path) {
        this.path = path;
    }
    //add override annotation
    public List<Product> read() {
        List<Product> productList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        // Convert JSON string from file to Object //it is self described code. Remove a comment

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

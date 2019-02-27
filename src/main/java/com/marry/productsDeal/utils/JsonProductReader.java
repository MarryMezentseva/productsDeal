package com.marry.productsDeal.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.marry.productsDeal.entities.Product;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonProductReader {
    private String path;

    public JsonProductReader(String path) {
        this.path = path;
    }

    public List<Product> read() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        // Convert JSON string from file to Object

        return mapper.readValue(new File("productList.json"),
                new TypeReference<List<Product>>() {
                });


    }

}

package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;

import java.util.List;

/**
 * This interface is inherited to create readers to read products.
 */
public interface ProductsReader {
    List<Product> read();
}

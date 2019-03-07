package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBProductReader {

    private static final String DB_PORT = "5432";
    private static final String DB_DB_NAME = "productsDB";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:" + DB_PORT + "/" + DB_DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public List<Product> read() {
        List<Product> productList = new ArrayList<>();
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM public.products");
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String title = resultSet.getString("title");
                double price = Double.parseDouble(resultSet.getString("price"));
                productList.add(new Product(title, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
}

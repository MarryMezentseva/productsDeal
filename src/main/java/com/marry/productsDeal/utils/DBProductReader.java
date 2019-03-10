package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBProductReader {

    //looks fine, but next step is to read all following from .property file
    private static final String DB_PORT = "5432";
    private static final String DB_DB_NAME = "productsDB";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:" + DB_PORT + "/" + DB_DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";
    private Connection connection = null;// convert it to a local variable
    private Statement statement = null;// convert it to a local variable
    private ResultSet resultSet = null;// convert it to a local variable

    public List<Product> read() {
        List<Product> productList = new ArrayList<>();
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);// create standalone method that returns connection: Connection getConnection()
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
                    connection.close();//if exception trows here, statement and resultSet objects will stuck in the memory and will never be closed. Create separate try/catch statements
                }
                if (statement != null) {
                    statement.close();//same here
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

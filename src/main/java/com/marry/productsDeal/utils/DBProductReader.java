package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DBProductReader implements ProductsReader {
    private String dbConnection;
    private String dbDriver;
    private String dbUser;
    private String dbPassword;
    private Connection connection;
    private FileInputStream fis;


    public DBProductReader(String filePath) {
        Properties property = new Properties();
        try {
            fis = new FileInputStream(filePath);
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        dbConnection = property.getProperty("dbconnection");
        dbDriver = property.getProperty("dbdriver");
        dbUser = property.getProperty("dbuser");
        dbPassword = property.getProperty("dbpassword");

    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
            Class.forName(dbDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Product> read() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM public.products");
            while (resultSet.next()) {
                // int id = Integer.parseInt(resultSet.getString("id"));
                String title = resultSet.getString("title");
                double price = Double.parseDouble(resultSet.getString("price"));
                productList.add(new Product(title, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
//
        }
        return productList;
    }
}

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
    private static final String SQL = "SELECT * FROM public.products";



    public DBProductReader(String filePath) {
        FileInputStream fis = null;
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
        dbConnection = property.getProperty("db.connection");
        dbDriver = property.getProperty("db.driver");
        dbUser = property.getProperty("db.user");
        dbPassword = property.getProperty("db.password");

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
            Class.forName(dbDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Product> read() {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Product> productList = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                double price = Double.parseDouble(resultSet.getString("price"));// convert "price" to constant
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

        }
        return productList;
    }
}

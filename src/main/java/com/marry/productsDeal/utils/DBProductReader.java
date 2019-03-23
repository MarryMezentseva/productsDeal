package com.marry.productsDeal.utils;

import com.marry.productsDeal.entities.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DBProductReader implements ProductsReader {

    private static final String SQL = "SELECT * FROM public.products";
    private static final String TITLE_COLUMN = "title";
    private static final String PRICE_COLUMN = "price";

    private String dbConnection;
    private String dbDriver;
    private String dbUser;
    private String dbPassword;

    public DBProductReader(String filePath) {
        try {
            init(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBProductReader(InputStream inputStream) {
       init(inputStream);
    }

    private void init(InputStream inputStream){
        Properties property = new Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
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
                String title = resultSet.getString(TITLE_COLUMN);
                double price = Double.parseDouble(resultSet.getString(PRICE_COLUMN));
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

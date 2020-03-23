package com.example.library.bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost/Lib";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "12345";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            //System.out.println("We have connection");//test

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //System.out.println("We don't have connection");//test
        }
        return connection;
    }
}

package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "Brandin2007$");

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Products";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String name = results.getString("ProductName");
                System.out.println(name);
            }
            connection.close();
        } catch (Exception e) {

        }
    }
}

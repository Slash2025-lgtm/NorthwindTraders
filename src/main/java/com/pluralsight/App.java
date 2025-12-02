package com.pluralsight;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.Scanner;

public class App {
    private static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        Connection connection = null;
        try {
            String url = args[0];
            String user = args[1];
            String password = args[2];

            connection = DriverManager.getConnection(url, user, password);

            boolean exit = false;
            while (!exit) {
                System.out.println("What do you want to do?");
                System.out.println("\t1) Display all products");
                System.out.println("\t2) Display all customers");
                System.out.println("\t3) Display all categories");
                System.out.println("\t0) Exit");
                System.out.print("Enter an option above: ");
                String option = keyboard.nextLine();

                switch (option) {
                    case "1" -> displayData(connection, "Products");
                    case "2" -> displayData(connection, "Customers");
                    case "3" -> displayData(connection, "Categories");
                    case "0" -> exit = true;
                    default -> System.out.println("Something went wrong try again");
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("something went wrong when closing your connection");
                }
            }
        }
    }

    public static void displayData(Connection connection, String type) {
        Statement statement = null;
        ResultSet results = null;

        try {
            String query = "SELECT * FROM " + type;
            statement = connection.prepareStatement(query);
            results = statement.executeQuery(query);
            if (type.equalsIgnoreCase("Customers")) {
                while (results.next()) {
                    String contactName = results.getString("ContactName");
                    String companyName = results.getString("CompanyName");
                    String city = results.getString("City");
                    String country = results.getString("Country");
                    String phoneNumber = results.getString("Phone");

                    System.out.println("===============================");
                    System.out.println("Contact Name: " + contactName);
                    System.out.println("Company Name: " + companyName);
                    System.out.println("City: " + city);
                    System.out.println("Country: " + country);
                    System.out.println("Phone Number: " + phoneNumber);
                    System.out.println("===============================");
                    System.out.println();
                }
            } else if (type.equalsIgnoreCase("Products")) {
                while (results.next()) {
                    int id = results.getInt("ProductID");
                    String name = results.getString("ProductName");
                    double unitPrice = results.getInt("UnitPrice");
                    int unitsInStock = results.getInt("UnitsInStock");

                    System.out.println("===============================");
                    System.out.println("Product Id: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Price: " + unitPrice);
                    System.out.println("Units In Stock: " + unitsInStock);
                    System.out.println("===============================");
                    System.out.println();
                }
            } else if (type.equalsIgnoreCase("Categories")) {
                while (results.next()) {
                    int categoryID = results.getInt("CategoryID");
                    String categoryName = results.getString("CategoryName");
                    System.out.println("===============================");
                    System.out.println("Category Id: " + categoryID);
                    System.out.println("Category Name: " + categoryName);
                    System.out.println("===============================");
                    System.out.println();
                }
            }
        } catch(SQLException e){
            System.out.println("Something has gone wrong in getting all you code");
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    System.out.println("something went wrong when closing your result");
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("something went wrong when closing your statement");
                }
            }
        }
    }
}
package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    private static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "Brandin2007$");
            System.out.println("What do you want to do?");
            System.out.println("\t1) Display all products");
            System.out.println("\t2) Display all customers");
            System.out.println("\t0) Exit");
            System.out.print("Enter an option above: ");
            String option = keyboard.nextLine();

            switch (option) {
                case "1" -> productsDisplay(connection);
                case "2" -> customerDisplay(connection);
                case "0" -> System.exit(0);
                default -> System.out.println("Something went wrong try again");
            }
        } catch (Exception e) {

        }
    }

    public static void productsDisplay(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Products";

            ResultSet results = statement.executeQuery(query);

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
            connection.close();
            statement.close();
            results.close();
        } catch(Exception e){

        }
    }

    public static void customerDisplay(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Customers";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String  contactName = results.getString("ContactName");
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
            connection.close();
            statement.close();
            results.close();
        } catch(Exception e){

        }
    }
}
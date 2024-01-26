package com.picsart;

import java.util.Scanner;

public class Start {

    /**
     * The main entry point for the Bookstore Management System.
     */
    public static void start() {

        Scanner scanner = new Scanner(System.in);
        CLI cli = new CLI();


        System.out.println("Connected to the database!");

        while (true) {
            cli.displayMenu();
            String choice = cli.getChoice(scanner);
            switch (choice) {
                case "1" -> cli.handleBooksManagement(scanner);
                case "2" -> cli.handleCustomersManagement(scanner);
                case "3" -> cli.handleSalesProcessing(scanner);
                case "4" -> cli.generateReports(scanner);
                case "5" -> System.out.println("Exiting the Bookstore Management System");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }

            if (choice.equals("5")) {
                break;
            }

            System.out.println("Connection closed");
        }
    }
}

package com.picsart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CLI {

    /**
     * Displays the main menu of the Bookstore Management System.
     */
    public void displayMenu() {
        System.out.println("Bookstore Management System Menu:");
        System.out.println("1. Book Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Sales Processing");
        System.out.println("4. Sales Reports");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    /**
     * Gets the user's choice from the main menu.
     *
     * @param scanner The Scanner object for user input.
     * @return The user's choice as a String.
     */
    public String getChoice(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Displays the menu for managing books and handles user input for book-related actions.
     *
     * @param scanner The Scanner object for user input.
     */
    public void handleBooksManagement(Scanner scanner) {
        displayForBooksManagement(scanner);
    }

    /**
     * Updates the details of a book based on user input.
     *
     * @param scanner The Scanner object for user input.
     */
    private void updateBooks(Scanner scanner) {
        System.out.println("Please enter the ID of the book to update");
        int bookID = generateValidNumber(scanner);
        scanner.nextLine();
        System.out.println("Please enter the new title");
        String newTitle = scanner.nextLine().trim();
        System.out.println("Please enter the new author");
        String newAuthor = scanner.nextLine().trim();
        System.out.println("Please enter the new genre");
        String newGenre = scanner.nextLine().trim();
        System.out.println("Please enter the new price");
        while ((!scanner.hasNextDouble())) {
            System.out.println("Invalid statement, please enter a number");
            scanner.next();
        }
        double newPrice = scanner.nextDouble();
        System.out.println("Please enter the new new quantity in stock");
        int newQuantityInStock = generateValidNumber(scanner);
        scanner.nextLine();

        Book book = new Book(
                bookID,
                newTitle,
                newAuthor,
                newGenre,
                newPrice,
                newQuantityInStock
        );
        BooksManagement booksManagement = new BooksManagement();
        booksManagement.updateBookDetails(book);
    }

    /**
     * Searches for books based on user-specified criteria.
     *
     * @param scanner The Scanner object for user input.
     */
    private void searchBooks(Scanner scanner) {
        String searchBy;
        do {
            System.out.println("Enter 'genre' or 'author' to search books:");
            searchBy = scanner.nextLine().trim().toLowerCase();
        } while (!((searchBy.equalsIgnoreCase("genre")) || (searchBy.equalsIgnoreCase("author"))));
        System.out.println("Enter the value to search for:");
        String searchValue = scanner.nextLine();
        searchValue = searchValue.substring(0, 1).toUpperCase() + searchValue.substring(1);

        BooksManagement booksManagement = new BooksManagement();
        booksManagement.searchBooksByGenreOrAuthor(searchBy, searchValue);
    }

    /**
     * Displays the menu for managing books and handles user input for book-related actions.
     *
     * @param scanner The Scanner object for user input.
     */
    private void displayForBooksManagement(Scanner scanner) {

        while (true) {
            System.out.println("Books Management System Menu.");
            System.out.println("1. Update book details.");
            System.out.println("2. Search books by genre or author.");
            System.out.println("3. Exit");
            System.out.println("Enter your choice 1, 2 or 3");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> updateBooks(scanner);
                case "2" -> searchBooks(scanner);
                case "3" -> System.out.println("Existing books management system");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }

            if (choice.equals("3")) {
                break;
            }
        }
    }

    /**
     * Displays the menu for managing customers and handles user input for customer-related actions.
     *
     * @param scanner The Scanner object for user input.
     */
    public void handleCustomersManagement(Scanner scanner) {
        displayForCustomersManagement(scanner);
    }

    /**
     * Displays the menu for managing customers and handles user input for customer-related actions.
     *
     * @param scanner The Scanner object for user input.
     */
    private void displayForCustomersManagement(Scanner scanner) {
        while (true) {
            System.out.println("Customers Management System Menu.");
            System.out.println("1. Update customers information.");
            System.out.println("2. View a customer’s purchase history.");
            System.out.println("3. Exit");
            System.out.println("Enter your choice 1, 2 or 3");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> updateCustomersInformation(scanner);
                case "2" -> customersPurchaseHistory(scanner);
                case "3" -> System.out.println("Existing books management system");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }

            if (choice.equals("3")) {
                break;
            }
        }
    }

    /**
     * Updates customer information based on user input.
     *
     * @param scanner The Scanner object for user input.
     */
    private void updateCustomersInformation(Scanner scanner) {
        Customer customer = new Customer();
        System.out.println("Enter the ID of the customer to update:");
        int customerIdToUpdate = generateValidNumber(scanner);
        scanner.nextLine();
        customer.setCustomerID(customerIdToUpdate);
        System.out.println("Enter the new  name:");
        String newName = scanner.nextLine();
        customer.setName(newName);
        System.out.println("Enter the new email:");
        String newEmail = scanner.nextLine();
        customer.setEmail(newEmail);
        System.out.println("Enter the new phone:");
        String newPhone = scanner.nextLine();
        PhoneNumberValidation phoneNumberValidation = new PhoneNumberValidation();
        while (!phoneNumberValidation.phoneNumberValid(newPhone)) {
            newPhone = scanner.nextLine();
        }
        customer.setPhone(newPhone);

        CustomersManagement customersManagement = new CustomersManagement();
        customersManagement.updateCustomer(customer);
    }

    /**
     * Displays the purchase history of a customer based on user input.
     *
     * @param scanner The Scanner object for user input.
     */
    private void customersPurchaseHistory(Scanner scanner) {
        System.out.println("Enter customer id");
        int customerIdToUpdate = generateValidNumber(scanner);

        CustomersManagement customersManagement = new CustomersManagement();
        customersManagement.viewCustomerPurchaseHistory(customerIdToUpdate);
    }

    /**
     * Displays the menu for sales processing and handles user input for sales-related actions.
     *
     * @param scanner The Scanner object for user input.
     */
    public void handleSalesProcessing(Scanner scanner) {
        SalesProcessing salesProcessing = new SalesProcessing();
        displayForSalesProcessing(scanner, salesProcessing);
    }

    /**
     * Displays the menu for sales processing and handles user input for sales-related actions.
     *
     * @param scanner          The Scanner object for user input.
     * @param salesProcessing  The SalesProcessing object for performing sales-related operations.
     */
    private void displayForSalesProcessing(Scanner scanner, SalesProcessing salesProcessing) {

        while (true) {
            System.out.println("Sales  Management System Menu.");
            System.out.println("1. Process for new sale.");
            System.out.println("2. Calculate total revenue by genre.");
            System.out.println("3. Exit");
            System.out.println("Enter your choice 1, 2 or 3");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> insertSalesProcessing(scanner);
                case "2" -> salesProcessing.calculateTotalRevenueByGenre();
                case "3" -> System.out.println("Existing books management system");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }

            if (choice.equals("3")) {
                break;
            }
        }
    }

    /**
     * Processes a new sale based on user input.
     *
     * @param scanner The Scanner object for user input.
     */
    private void insertSalesProcessing(Scanner scanner) {
        System.out.println("Enter the customer ID:");
        int customerId = generateValidNumber(scanner);
        Customer customer = new Customer(customerId);
        System.out.println("Enter the book ID:");
        int bookId = generateValidNumber(scanner);
        Book book = new Book(bookId);
        System.out.println("Enter the quantity:");
        int quantity = generateValidNumber(scanner);
        scanner.nextLine();

        System.out.println("Enter the date of sale:");
        String str = scanner.nextLine();
        ValidationDate validationDate = new ValidationDate();
        while (!validationDate.validDateFormat(str)) {
            System.out.println("Invalid statement, please enter the valid date of birth");
            str = scanner.nextLine();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        java.sql.Date sqlDate = null;
        try {
            java.util.Date utilDate = dateFormat.parse(str);
            sqlDate = new java.sql.Date(utilDate.getTime());

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        SalesProcessing salesProcessing = new SalesProcessing();
        salesProcessing.processNewSale(customer, book, sqlDate, quantity);
    }

    /**
     * Displays the menu for generating sales reports and handles user input for report-related actions.
     *
     * @param scanner      The Scanner object for user input.
     * @param salesReports The SalesReports object for generating sales reports.
     */
    private void displayForReports(Scanner scanner, SalesReports salesReports) {
        while (true) {
            System.out.println("Sales Reports System Menu.");
            System.out.println("1. Books sales reports.");
            System.out.println("2. Revenue report by genre.");
            System.out.println("3. Exit");
            System.out.println("Enter your choice 1, 2 or 3");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> salesReports.generateBooksReport();
                case "2" -> salesReports.generateRevenueReportByGenre();
                case "3" -> System.out.println("Existing books management system");
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }

            if (choice.equals("3")) {
                break;
            }
        }
    }

    /**
     * Displays the sales reports menu and handles user input for generating reports.
     *
     * @param scanner The Scanner object for user input.
     */
    public void generateReports(Scanner scanner) {
        SalesReports salesReports = new SalesReports();
        displayForReports(scanner, salesReports);
    }

    /**
     * Generates a valid number from user input, handling invalid inputs.
     *
     * @param scanner The Scanner object for user input.
     * @return The valid number entered by the user.
     */
    private int generateValidNumber(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid statement, please enter a number");
            scanner.next();
        }
        return scanner.nextInt();
    }
}

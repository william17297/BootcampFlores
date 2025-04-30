package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private List<Transaction> transactions;

    public Ledger() {
        this.transactions = TransactionFileManager.readFiles();  //reads from the list as appose to making a new list
    }

    public void newTransactionToFile(Transaction transaction) throws InterruptedException {
        transactions.add(transaction);
        TransactionFileManager.appendTransaction(transaction);
        System.out.println("Deposit has been added");
    }

    public void addEntry(Scanner scanner, int userSelection) throws InterruptedException {
        try {
            System.out.println("What is the Vendor's name?");
            String vendor = scanner.nextLine();
            double amount = 0;
            if (userSelection == 1) {     //To display this in case 1
                System.out.println("What is the amount paid? ");
                amount = Double.parseDouble(scanner.nextLine());
            } else {   //To display this in case 2
                System.out.println("What is the cost? ");
                amount = Double.parseDouble(scanner.nextLine());
                amount *= -1;
            }
            System.out.println("Give a description:");
            String description = scanner.nextLine();
            newTransactionToFile(new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount));
        } catch (NumberFormatException ex) {
            System.out.println("Thats not right");
        }
    }

    public void displayEntries(Scanner scanner) {
        System.out.println("Ledger");
        System.out.println("1.) Display All");
        System.out.println("2.) Display Deposits");
        System.out.println("3.) Display Payments");
        System.out.println("4.) Display Reports");
        System.out.println("5.) Home");

        int userInput = Integer.parseInt(scanner.nextLine());
        switch(userInput){
            case 1:
                for (int i = 0; i < transactions.size(); i++) {

                }
                System.out.println(transactions.get(0));
                case 2:
                    individualEntry(userInput);
                break;
                case 3:
                    individualEntry(userInput);
                break;
                case 4:
                    reports(scanner);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Please pick 1 through 5");
        }

    }
    public void individualEntry( int userInput) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() > 0 && userInput == 1) {
                System.out.println(transactions.get(i).toString());
            } else if (transactions.get(i).getAmount() < 0 && userInput == 2) {
                System.out.println(transactions.get(i).toString());
            }
        }
    }
    public void reports(Scanner scanner){
            boolean userHasNotSelected = true;

            while (userHasNotSelected) {
                System.out.println("\n--- Reports ---");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To Date");
                System.out.println("4) Previous Year");
                System.out.println("5) Search by Vendor");
                System.out.println("0) Back");

                String userChoice = scanner.nextLine();

                switch (userChoice) {
                    case "1":
                       compareMonths(1);
                        break;
                    case "2":
                        compareMonths(2);
                        break;
                    case "3":
                        compareYears(3);
                        break;
                    case "4":
                        compareYears(4);
                        break;
                    case "5":
                        searchByVendor(scanner);
                        break;
                    case "0":
                        userHasNotSelected = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose from 0 to 5.");
                }
            }

    }

    public void compareMonths(int userChoice){
        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);
            LocalDate dateOfTransaction = currentTransaction.getDate();
            Month monthOfTransaction = dateOfTransaction.getMonth();
            Month monthOfCurrentDate = LocalDate.now().getMonth();
            if(monthOfTransaction == monthOfCurrentDate && userChoice == 1) {
                System.out.println(currentTransaction.toString());
            }
            else if (monthOfTransaction == monthOfCurrentDate.minus(1) && userChoice == 2){
                    System.out.println(currentTransaction.toString());
            }
        }

    }
    public void compareYears(int userChoice){
        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);
            LocalDate dateOfTransaction = currentTransaction.getDate();
            int yearOfTransaction = dateOfTransaction.getYear();
            int yearOfCurrentDate = LocalDate.now().getYear();
            if(yearOfTransaction == yearOfCurrentDate && userChoice == 3) {
                System.out.println(currentTransaction.toString());
            }
            else if (yearOfTransaction == (yearOfCurrentDate - 1) && userChoice == 4){
                System.out.println(currentTransaction.toString());
            }
        }

    }
    public void searchByVendor(Scanner scanner){
        System.out.println("What is the vendor's name?\nVendor: ");
        String userVendor = scanner.nextLine().toLowerCase();

        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);
            String currentVendor = currentTransaction.getVendor();
            if(currentVendor.equalsIgnoreCase(userVendor)){
                System.out.println(currentTransaction.toString());
            }
        }
    }
}
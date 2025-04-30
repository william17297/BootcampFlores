package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    //private Transaction transaction;
    private List<Transaction> transactions;

    public Ledger() {
        //this.transactions = new ArrayList<>();
        this.transactions = TransactionFileManager.readFiles();  //reads from the list as appose to making a new list
    }

    public void newTransactionToFile(Transaction transaction) throws InterruptedException {
        //List<Transaction> this.transactions = TransactionFileManager.readFiles();
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
                    individualEntry(scanner , userInput);
                break;
                case 3:
                    individualEntry(scanner , userInput);
                break;
                case 4:

                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Please pick 1 through 5");
        }

    }
    public void individualEntry(Scanner scanner , int userInput) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() > 0 && userInput == 1) {
                System.out.println(transactions.get(i).toString());
            } else if (transactions.get(i).getAmount() < 0 && userInput == 2) {
                System.out.println(transactions.get(i).toString());
            }
        }
    }
    public void reports(){

    }
}
package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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

            System.out.println("Vendor:");
            String vendor = scanner.nextLine();
            double amount = 0;
            boolean userHasNotSelected2 = true;
            while (userHasNotSelected2) {
                try {
                    if (userSelection == 1) {     //To display this in case 1
                        System.out.print("Amount paid: ");
                        amount = Double.parseDouble(scanner.nextLine());
                        userHasNotSelected2 = false;
                    } else if (userSelection == 2) {   //To display this in case 2
                        System.out.print("Cost: ");
                        amount = Double.parseDouble(scanner.nextLine());
                        amount *= -1;
                        userHasNotSelected2 = false;
                    }
                        System.out.print("Description: ");
                        String description = scanner.nextLine();

                        System.out.println();
                        newTransactionToFile(new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount));


                }
                    catch (NumberFormatException ex) {
                    System.out.println("PLEASE ENTER A DIGIT(NUMBER)");
                }
            }
        }

    public void displayEntries(Scanner scanner)throws InterruptedException{
        boolean hasNotSelected = true;
        while (hasNotSelected) {
            try {
                System.out.println("\n--- Ledger ---");
                System.out.println("1.) Display All");
                System.out.println("2.) Display Deposits");
                System.out.println("3.) Display Payments");
                System.out.println("4.) Display Reports");
                System.out.println("5.) Back");
                int userInput = Integer.parseInt(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        for (int i = 0; i < transactions.size(); i++) {
                            System.out.println(transactions.get(i).toString());
                            Thread.sleep(250);
                        }
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
                        hasNotSelected = false;
                        break;
                    default:
                        System.out.println("\n\nInvalid Selection. Please choose from 1 to 5.");
                }

            }
            catch (NumberFormatException ex){
                System.out.println("\n\nInvalid Selection. Please choose from 1 to 5.");
            }
        }
    }
    public void individualEntry( int userInput)  {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() > 0 && userInput == 2) {
                System.out.println(transactions.get(i).toString());
            } else if (transactions.get(i).getAmount() < 0 && userInput == 3) {
                System.out.println(transactions.get(i).toString());
            }
        }
    }
    public void reports(Scanner scanner)  {
            boolean userHasNotSelected = true;

            while (userHasNotSelected) {
                System.out.println("\n--- Reports ---");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To Date");
                System.out.println("4) Previous Year");
                System.out.println("5) Custom Search");
                System.out.println("6) Back");

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
                        customSearch(scanner);
                        break;
                    case "6":
                        userHasNotSelected = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose from 1 to 6.");
                }
            }

    }

    public void compareMonths(int userChoice) {
        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);
            LocalDate dateOfTransaction = currentTransaction.getDate();
            Month monthOfTransaction = dateOfTransaction.getMonth();
            Month monthOfCurrentDate = LocalDate.now().getMonth();
            if(monthOfTransaction == monthOfCurrentDate && dateOfTransaction.getYear() == LocalDate.now().getYear() && userChoice == 1) {
                System.out.println(currentTransaction.toString());
            }
            else if (monthOfTransaction == monthOfCurrentDate.minus(1) && dateOfTransaction.getYear() == LocalDate.now().getYear() && userChoice == 2){
                    System.out.println(currentTransaction.toString());
            }
        }

    }
    public void compareYears(int userChoice) {
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
    public void filterByVendor(Scanner scanner) {
        System.out.println("Vendor: ");
        String userVendor = scanner.nextLine().toLowerCase();
        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);
            String currentVendor = currentTransaction.getVendor();
            if(currentVendor.equalsIgnoreCase(userVendor)){
                System.out.println(currentTransaction.toString());
            }
        }
    }
    public void filterByDate (Scanner scanner) {
        System.out.println("Enter: Start Date");
        LocalDate userStartDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Enter: End Date");
        LocalDate userEndDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);

            if(currentTransaction.getDate().isAfter(userStartDate) && currentTransaction.getDate().isBefore(userEndDate)){
                System.out.println(currentTransaction.toString());
            }
        }
    }
    public void filterByDescription (Scanner scanner) {
        System.out.println("Description: ");
        String userDescription = scanner.nextLine().toLowerCase();
        for(int i = 0; i < transactions.size(); i++){
            Transaction currentTransaction = transactions.get(i);
            String currentDescription = currentTransaction.getDescription();
            if(currentDescription.equalsIgnoreCase(userDescription)){
                System.out.println(currentTransaction.toString());
            }
        }
    }
    public void filterByDeposit (Scanner scanner) {
        System.out.println("Amount: ");
        try {
            String userAmount = scanner.nextLine();
            double userAmountDouble = Double.parseDouble(userAmount);
                for (int i = 0; i < transactions.size(); i++) {
                    Transaction currentTransaction = transactions.get(i);
                    double currentAmount = currentTransaction.getAmount();
                    if (currentAmount == userAmountDouble) {
                        System.out.println(currentTransaction.toString());
                    }
                }
        }catch (NumberFormatException ex){
            System.out.println("Please enter a number");
        }
    }
    public void filterByPayment (Scanner scanner) {
        System.out.println("Payment: ");
        try {
            String userAmount = scanner.nextLine();
            double userAmountDouble = Double.parseDouble(userAmount)*-1;
            for (int i = 0; i < transactions.size(); i++) {
                Transaction currentTransaction = transactions.get(i);
                double currentAmount = currentTransaction.getAmount();
                if (currentAmount == userAmountDouble) {
                    System.out.println(currentTransaction.toString());
                }
            }
        }catch (NumberFormatException ex){
            System.out.println("Please enter a number");
        }
    }
    public void customSearch(Scanner scanner) {
        boolean userHasNotSelected = true;
        while (userHasNotSelected) {
            try {
                System.out.println("\nFilter by: ");
                System.out.println("1.) Date ");
                System.out.println("2.) Vendor ");
                System.out.println("3.) Description ");
                System.out.println("4.) Payment");
                System.out.println("5.) Deposit ");
                System.out.println("6.) Back ");

                int userChoice = Integer.parseInt(scanner.nextLine());
                switch (userChoice) {
                    case 1:
                        filterByDate(scanner);
                        break;
                    case 2:
                        filterByVendor(scanner);
                        break;
                    case 3:
                        filterByDescription(scanner);
                        break;
                    case 4:
                        filterByPayment(scanner);
                        break;
                    case 5:
                        filterByDeposit(scanner);
                        break;
                    case 6:
                        userHasNotSelected = false;
                        break;
                    default:
                        System.out.println("Invalid Selection. Please pick 1 through 6.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Selection. Please pick 1 through 6.");
            }
        }
    }
}
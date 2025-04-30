package org.example;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.UnknownFormatConversionException;

public class UserInterface {
    public static void startProgram() throws InterruptedException {
        Ledger ledger = new Ledger();
        Scanner scanner = new Scanner(System.in);
        boolean incorrectlySelected = false;
        System.out.println("\n\nWelcome to the Account Ledger App\n--------------------------------\n\n");
        Thread.sleep(700);
        while (true) {
            try {

                if(incorrectlySelected) {
                    System.out.println("\n\n\n\nWhat would you like to do? (PLEASE SELECT 1 THROUGH 4)");
                    incorrectlySelected = false;
                }
                else {
                    System.out.println("\n\n\n\nWhat would you like to do?");
                }
                System.out.println("1.) Add Deposit");
                System.out.println("2.) Make Payment (Debit)");
                System.out.println("3.) Ledger");
                System.out.println("4.) Exit");

                int userSelection = Integer.parseInt(scanner.nextLine());

                switch (userSelection){
                    case 1:
                        ledger.addEntry(scanner , 1);
                        break;
                    case 2:
                        ledger.addEntry(scanner ,2);
                        break;
                    case 3:
                        ledger.displayEntries(scanner);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        incorrectlySelected = true;
                }

            }
            catch (NumberFormatException ex){
                incorrectlySelected = true;
            }
        }
    }




}

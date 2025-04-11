package org.example;

import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter initial deposit:");
        double initialDeposit = scanner.nextDouble(); // This is P

        System.out.println("Enter interest rate:");
        double yearlyInterestRate = scanner.nextDouble(); //This is r

        System.out.println("Enter number of years deposit has earned interest for:");
        double yearsInvested = scanner.nextDouble(); // This is t

        double futureValue = initialDeposit * Math.pow(1 + ((yearlyInterestRate/365)/100) , 365 * yearsInvested);
        System.out.println("You  would have earned $"+ futureValue + " in interest");
    }
}

package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your loan amount:");
    double principal = scanner.nextDouble(); //This is P (Loan amount)

        System.out.println("Enter Interest Rate:");
    //Used to calculate r
    double annualInterestRate = scanner.nextDouble();

        System.out.println("Enter loan Length:");
    //Used to calculate n
    double loanLength = scanner.nextDouble();


    double perMonthInterestRate = (annualInterestRate/12)/100; //This is r

    //Used to calculate M
    double totalMonthlyPayment = loanLength * 12; //This is n

    double monthlyPayment = principal*(Math.abs(perMonthInterestRate*Math.pow(1+perMonthInterestRate , totalMonthlyPayment))
            / Math.abs(Math.pow(1 + perMonthInterestRate , totalMonthlyPayment)-1)); //This is M

    double totalInterest = (monthlyPayment * totalMonthlyPayment) - principal;

    System.out.println("Your monthly payment is:\n" + monthlyPayment + "\nYour total interest is:\n" + totalInterest);

    }
}
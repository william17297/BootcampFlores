package org.example;

import java.util.Scanner;

public class Calculator3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the present value of the annuity?");
        double pmt = scanner.nextDouble(); //This is PMT
        System.out.println("What is the interest?");
        double  yearlyInterestRate = scanner.nextDouble(); //To solve r
        System.out.println("What is the numbers of years to pay out?");
        double  years = scanner.nextDouble(); //To solve N

        double monthlyInterestRate = (yearlyInterestRate/12)/100; //this is R
        double  months =  years * 12; //This is N

        double pv = pmt * Math.abs((1 - Math.pow(1 + monthlyInterestRate , -months))/monthlyInterestRate);
        System.out.println("The present value of the annuity is:" + pv);
    }
}

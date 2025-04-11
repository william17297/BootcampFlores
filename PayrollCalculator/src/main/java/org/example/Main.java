package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println("How many hours did you work?");
        float hoursWorked = scanner.nextFloat();
        System.out.println("What is your hourly pay?");
        float payRate = scanner.nextFloat();
        float grossPay = 0f;
        if (hoursWorked > 40){
            grossPay = hoursWorked * payRate * 1.5f;
        }
        else {
             grossPay = hoursWorked * payRate;
        }
        System.out.println("Hi " +  name + " your gross pay is " + grossPay);

    }
}
package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        double input1 = scanner.nextDouble();
        System.out.println("Enter the second number: ");
        double input2 = scanner.nextDouble();
        System.out.println("The total of the two numbers is " + (input1+input2));

    }
}
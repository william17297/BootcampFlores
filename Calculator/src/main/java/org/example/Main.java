package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        double input1 = scanner.nextDouble();
        System.out.println("Enter the second number: ");
        double input2 = scanner.nextDouble();
        System.out.print("Possible calculations:\n(A)dd\n(S)ubtract\n(M)ultipy\n(D)ivide\nplease select an option:");
        scanner.nextLine();
        char character = scanner.nextLine().charAt(0);

        switch(character){
            case 'a':

                System.out.println("if added together it is " + (input1 + input2));
                break;
            case 's':
                System.out.println("if subtracted together it is " + (input1 - input2));
                break;
            case 'm':
                System.out.println("if multiplied together it is " + (input1 * input2));
                break;
            case 'd':
                System.out.println("if divided together it is " + (input1 / input2));
                break;
            default:
                System.out.println("That is not one of the operations. Please try again.");

        }
    }
}
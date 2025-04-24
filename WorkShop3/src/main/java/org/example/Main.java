package org.example;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        userInterface();
        }

    public static void userInterface() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isNotFirstTime = false;
        System.out.println("\n\nWelcome to the online Store!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n");
        Thread.sleep(1000);
        System.out.println("\n\nWhat would you like to do? (pick 1 through 3)");
        System.out.println("---------------------------");

        while(true){
            try {
                System.out.println("1.) Display Products");
                System.out.println("2.) Display Cart");
                System.out.println("3.) Exit");
                int mainMenuSelection = Integer.parseInt(scanner.nextLine());
                switch (mainMenuSelection) {
                    case 1:
                        displayProducts(scanner);
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n -- Thank you for shopping with us! --\n\n\n\n");
                        Thread.sleep(1000);
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\n\n\n -- Please select a number between 1 through 3 --\n\n\n");
                        //System.out.println("\n\n");
                        break;

                }
            }

            catch (NumberFormatException ex){
                System.out.println("\n\n\n -- Please select a number between 1 through 3 --\n\n\n");
                //System.out.println("\n\n");
            }
        }
    }
    public static void displayProducts(Scanner scanner) throws InterruptedException {
        System.out.print("\nHere is a list of our available products.\n");
        System.out.println("-----------------------------------------");
        List<Product> productList = FileLoader.readFile();

        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            System.out.println((i + 1) + ".) " + currentProduct.getProductName());
            Thread.sleep(500);
        }
        System.out.print("\nWhat would you like to do? (pick 1 through 3)\n");
        System.out.println("-----------------------------");
        System.out.println("1.) \uD83D\uDD0D Search for specific product");
        System.out.println("2.) \uD83D\uDED2 Add a product to your cart");
        System.out.println("3.) \u2B05 Go back");
        boolean stay = true; //To go back when the user wants to leave
        while (stay) {
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("\n\n\n\n -- Please select a number between 1 through 3 --");
                                System.out.println("   --------------------------------------------\n\n\n");
                        stay = false;

                        break;
                    default:
                        System.out.println("\n");
                        System.out.println("\n\n\n\n -- Please select a number between 1 through 3 --\n\n\n\n");

                        break;
                }
            }

            catch(NumberFormatException ex){
                System.out.println("\n");
            System.out.println("\n\n\n\n -- Please select a number between 1 through 3 --\n\n\n\n");

            }
        }
    }
}
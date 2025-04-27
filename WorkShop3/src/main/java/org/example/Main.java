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
        ShoppingCart shoppingCart = new ShoppingCart();
        System.out.println("\n\nWelcome to the online Store!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n");
        Thread.sleep(700);
        while (true) {
            try {
                System.out.println("\n\n\nWhat would you like to do?");
                System.out.println("---------------------------");

                System.out.println("1.) \uD83D\uDCE6 Display Products");
                System.out.println("2.) \uD83D\uDED2 Display Cart");
                System.out.println("3.) \uD83D\uDED1 Exit");
                int mainMenuSelection = Integer.parseInt(scanner.nextLine());
                switch (mainMenuSelection) {
                    case 1:
                        displayProducts(scanner , shoppingCart);
                        break;
                    case 2:
                        shoppingCart.displayItemsInCart(scanner);
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n -- Thank you for shopping with us! --\n\n\n\n");
                        Thread.sleep(1000);
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\n\n\n -- Please select a number between 1 through 3 --");
                        break;

                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\n\n -- Please select a number between 1 through 3 --");
            }
        }
    }
    public static void displayProducts(Scanner scanner , ShoppingCart shoppingCart) throws InterruptedException {
        System.out.print("\nHere is a list of our available products.\n");
        System.out.println("-----------------------------------------");
        List<Product> productList = FileLoader.readFile();

        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            System.out.println((i + 1) + ".) " + currentProduct.getProductName());
            Thread.sleep(250);
        }
        boolean stay = true; //To go back when the user wants to leave
        while (stay) {
            System.out.print("\nWhat would you like to do?");
            System.out.println("\n-----------------------------");
            System.out.println("1.) \uD83D\uDD0D Search for specific product");
            System.out.println("2.) \uD83D\uDED2 Add a product to your cart");
            System.out.println("3.) \u2B05 Go back");
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        searchProducts(productList, scanner , shoppingCart);
                        break;
                    case 2:
                        selectProducts(productList, scanner , shoppingCart);
                        break;
                    case 3:
                        System.out.println("\n -- Please select a number between 1 through 3--"); //For the main menu
                        stay = false;

                        break;
                    default:
                        //System.out.println("\n");
                        System.out.println("\n\n -- Please select a number between 1 through 3 --\n\n");
                        break;
                }
            } catch (NumberFormatException ex) {
                //System.out.println("\n");
                System.out.println("\n\n -- Please select a number between 1 through 3 --\n\n");

            }
        }
    }
    public static void searchProducts(List<Product> productList, Scanner scanner , ShoppingCart shoppingCart) throws InterruptedException{
        System.out.print("Search by SKU: ");
        String userSearch = scanner.nextLine();
        boolean itemFound = false;
        boolean searching = true;
        boolean hasNotConfirm = true;
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (userSearch.equalsIgnoreCase(currentProduct.getSku())) {
                System.out.println("1 item matches your search: " + currentProduct.getSku() + " - " + currentProduct.getProductName());
                itemFound = true;
                System.out.println("Want to add the product to your cart? 1.) Yes or 2.) No");
                while(hasNotConfirm) {
                    try {
                        int userChoice = Integer.parseInt(scanner.nextLine());
                        if (userChoice == 1) {
                            shoppingCart.addProductToCart(currentProduct);
                            hasNotConfirm = false;
                        }
                        else if(userChoice == 2){
                            System.out.println("\n\n\n");
                            break;
                        }
                        else {
                            System.out.println("\n");
                            System.out.println("\n-- Please select 1.) Yes or 2.) No --");
                        }
                    }
                    catch (NumberFormatException ex){
                        System.out.println("\n");
                        System.out.println("\n-- Please select 1.) Yes or 2.) No --");
                    }
                }
                break;
            }
            else if(!itemFound && (i + 1) == productList.size()){
                System.out.println("\n\nWe do not have that item.\n\n");
                Thread.sleep(700);
            }

        }

    }
    public  static void selectProducts(List<Product> productList, Scanner scanner , ShoppingCart shoppingCart) throws InterruptedException{
        boolean hasNotSelected = true;
        boolean firstTime = true;
        System.out.println("\n\n\n\n");
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            System.out.println((i + 1) + ".) " + currentProduct.getProductName());
        }
            System.out.println("\nWhich item would you like to purchase? (select 1 - 12)");

        try {

            int userInput2 = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < productList.size(); i++) {
                Product currentProduct = productList.get(i);
                if ((userInput2 - 1) == i) {
                    shoppingCart.addProductToCart(currentProduct);
                    firstTime = false;
                    hasNotSelected = false;
                    break;
                } else if((i + 1) == productList.size()){
                    System.out.println("\n");
                    System.out.println("\n-- Please select a number between 1 through 12 --");
                    firstTime = false;
                    break;
                }
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("\n");
            System.out.println("\n-- Please select a number between 1 through 12 --");
        }
    }
}

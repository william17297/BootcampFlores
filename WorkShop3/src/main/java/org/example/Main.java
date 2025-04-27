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
        List<Product> productList = FileLoader.readFile();
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
                System.out.println("2.) \uD83D\uDED2 Go to cart");
                System.out.println("3.) \uD83D\uDED1 Exit");
                int mainMenuSelection = Integer.parseInt(scanner.nextLine());
                switch (mainMenuSelection) {
                    case 1:
                        displayProducts(scanner , shoppingCart ,productList);
                        break;
                    case 2:
                        UserOptions(scanner , shoppingCart);
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
    public static void displayProducts(Scanner scanner , ShoppingCart shoppingCart ,List<Product> productList) throws InterruptedException {
        System.out.print("\nHere is a list of our available products.\n");
        System.out.println("-----------------------------------------");


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
        boolean hasNotConfirm = true;
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (userSearch.equalsIgnoreCase(currentProduct.getSku())) {
                System.out.println("1 item matches your search: " + currentProduct.getSku() + " - " + currentProduct.getProductName());
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
    public static void selectProducts(List<Product> productList, Scanner scanner , ShoppingCart shoppingCart) throws InterruptedException{
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
                    break;
                } else if((i + 1) == productList.size()){
                    System.out.println("\n");
                    System.out.println("\n-- Please select a number between 1 through 12 --");
                    break;
                }
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("\n");
            System.out.println("\n-- Please select a number between 1 through 12 --");
        }
    }
    public static void UserOptions( Scanner scanner , ShoppingCart shoppingCart) throws InterruptedException{
        boolean hasNotSelectedBack = true;
        boolean hasDoneTransaction = false;
        while (hasNotSelectedBack && shoppingCart.numberOfItemsInCart() != 0) {
            try {
                System.out.println("What would you like to do?");
                System.out.println("1.) Check out");
                System.out.println("2.) Remove product from cart");
                System.out.println("3.) Back");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        checkOut(scanner , shoppingCart);
                        if(shoppingCart.numberOfItemsInCart() == 0){ //Just so that it doesn't run the if on line 197 once done purchasing
                            hasDoneTransaction = true;
                        }
                        break;
                    case 2:
                        System.out.println("\n\n\n\n" + "\n\n\n");
                        shoppingCart.displayItemsInCart();
                        System.out.println("\n\nType in the SKU of the product you would like to remove.");
                        String userSku = scanner.nextLine();
                        shoppingCart.removeProduct(userSku);
                        break;
                    case 3:
                        hasNotSelectedBack = false;
                        break;
                    default:
                        System.out.println("\n\n\n\nPlease pick a choice from 1 through 3\n\n\n\n");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\n\n\nPlease pick a choice from 1 through 3\n\n\n\n");
            }
        }
        if(shoppingCart.numberOfItemsInCart() == 0 && !hasDoneTransaction){
            System.out.println("\n\n\n\nThere are no items in your cart.\n\n\n\n");
            Thread.sleep(500);
        }
    }
    public static void checkOut(Scanner scanner , ShoppingCart shoppingCart ) throws InterruptedException {
        shoppingCart.displayItemsInCart();
        boolean hasChoosen = false;
        while (!hasChoosen) {
            try {
                System.out.println("Ready to Check out? 1.) Yes or 2.) No");
                int userOption = Integer.parseInt(scanner.nextLine());
                switch (userOption) {
                    case 1:
                        System.out.print("Enter Payment amount: ");
                        double userPayment = Double.parseDouble(scanner.nextLine());
                        if (userPayment < shoppingCart.getCartTotal()) {        //if user does not have enough money
                            System.out.println("You do not have enough money to pay!\n\n");
                        } else {
                            userPayment -= shoppingCart.getCartTotal(); //If user had the same amount
                            System.out.println("\n\n\n\nProcessing...\n\n\n\n");
                            Thread.sleep(500);
                            System.out.println("\n\n\n\n...\n\n\n\n");
                            Thread.sleep(500);
                            System.out.println("\n\n\n\nTransaction complete!\n\n\n\n");
                            Thread.sleep(500);
                            System.out.println("~~~~~~~~~Receipt~~~~~~~~~");
                            shoppingCart.displayItemsInCart();
                            System.out.println("Paid: " + shoppingCart.getCartTotal());
                            System.out.printf("Your change is $%.2f", userPayment);
                            hasChoosen = true;
                            shoppingCart.removeAllCartItems(); //clears shopping cart
                            Thread.sleep(1000);
                        }
                        break;
                    case 2:
                        hasChoosen = true;
                        break;
                    default:
                        System.out.println("Please type 1 or 2");
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Please type 1 or 2");
            }
        }
    }
}

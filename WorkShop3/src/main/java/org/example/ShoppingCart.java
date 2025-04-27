package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() { //constructor with no parameters
        this.products = new ArrayList<>();  //When we create a ShoppingCart instance, this is going to make a new list
    }

    //TODO add product to cart method
    public void addProductToCart(Product product) throws InterruptedException{
        products.add(product);
        System.out.println("\n\n\nItem has been added to your cart!\n\n\n");
        Thread.sleep(500);

    }

    //TODO remove product from cart method
    //You will need the SKU of the product you want to remove
    //Loop through the list of products
    //Check to see if the SKU matches
    //Get that Product, then use the remove method AFTER the loop

    public void removeProduct(String sku) throws InterruptedException {
        int index = 0;
        boolean productMatched = false;
            for (int i = 0; i < products.size(); i++) {
                Product currentProduct = products.get(i);
                if (sku.equalsIgnoreCase(currentProduct.getSku())) {
                    index = i;
                    productMatched = true;
                    break;
                } else if ((i + 1) == products.size()) {
                    System.out.println("\n\n\n\nThat is not a valid SKU\n\n\n\n");
                    break;
                }

            }
            if(productMatched) {
                products.remove(index);
                System.out.println("\nItem has been removed!\n");
                Thread.sleep(500);
            }

                //TODO (use one of the bulit in remove methods for strings)


    }

    //TODO get cart total method
    public double getCartTotal(double price) {

        return price;
    }

    public void displayItemsInCart(Scanner scanner) throws InterruptedException{
        boolean hasNotSelectedBack = true;
        if(!products.isEmpty()) {
            System.out.println("Items in cart:");
            double total = 0;
            for (int i = 0; i < products.size(); i++) {
                Product currentProduct = products.get(i);
                System.out.println("Sku: " + currentProduct.getSku() + " Name: " + currentProduct.getProductName() +
                        " Price: " + currentProduct.price + " Department: " + currentProduct.getDepartment());
                total += currentProduct.price;
            }
            System.out.println("\nYour total is: " + total + "\n");
        }
        else if(products.isEmpty()){
            System.out.println("");
            System.out.println("\n\n\n\nThere are no items in your cart.\n\n\n\n");
            Thread.sleep(500);
        }
            while (hasNotSelectedBack && !products.isEmpty()) {
                try {
                    System.out.println("What would you like to do?");
                    System.out.println("2.) Remove product from cart");
                    System.out.println("3.) Back");

                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:

                            break;
                        case 2:
                            System.out.println("\n\n\n\n" + "\n\n\n");
                            for (int i = 0; i < products.size(); i++) {
                                Product currentProduct = products.get(i);
                                System.out.println("Sku: " + currentProduct.getSku() + " Name: " + currentProduct.getProductName() +
                                        " Price: " + currentProduct.price + " Department: " + currentProduct.getDepartment());
                            }
                            System.out.println("\n\nType in the SKU of the product you would like to remove.");
                            String userSku = scanner.nextLine();
                            removeProduct(userSku);
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
    }
}

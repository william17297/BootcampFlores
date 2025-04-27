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
    }

    //TODO get cart total method
    public double getCartTotal() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            total += currentProduct.price;
        }
        return total;
    }

    public void displayItemsInCart() throws InterruptedException{

            System.out.println("Items in cart:");
            double total = 0;
            for (int i = 0; i < products.size(); i++) {
                Product currentProduct = products.get(i);
                System.out.println("Sku: " + currentProduct.getSku() + " Name: " + currentProduct.getProductName() +
                        " Price: " + currentProduct.price + " Department: " + currentProduct.getDepartment());
                total += currentProduct.price;
            }
            System.out.printf("\nTotal: $%.2f\n", total);



    }
    public int numberOfItemsInCart(){

        return products.size();
    }
    public void removeAllCartItems() throws InterruptedException {
        products.clear();
    }
}

package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() { //constructor with no parameters
        this.products = new ArrayList<>();  //When we create a ShoppingCart instance, this is going to make a new list
    }

    //TODO add product to cart method
    public void addProductToCart(Product product) {
        products.add(product);

    }

    //TODO remove product from cart method
    //You will need the SKU of the product you want to remove
    //Loop through the list of products
    //Check to see if the SKU matches
    //Get that Product, then use the remove method AFTER the loop

    public void removeProduct(String sku) {
        /*for(int i = 0; i < products.size(); i++){
             Product p = products.get(i);
             if(p sku){

             }
        }*/
    }

    //TODO get cart total method
    public double getCartTotal(double price) {

        return price;
    }

    public void displayItemsInCart(){
        System.out.println("Items in cart:");
        for(int i = 0; i < products.size(); i++ ){
            Product currentProduct = products.get(i);
            System.out.println("Sku: " + currentProduct.getSku() + "Name: " + currentProduct.getProductName() +
                    "Price: " + currentProduct.price + "Department: " + currentProduct.getDepartment());
        }
    }
}

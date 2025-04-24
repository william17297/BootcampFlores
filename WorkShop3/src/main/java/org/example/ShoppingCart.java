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
        throw new UnsupportedOperationException(); //Placeholder for code
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
    public double GetCartTotal(double price) {
        throw new UnsupportedOperationException();
    }
}

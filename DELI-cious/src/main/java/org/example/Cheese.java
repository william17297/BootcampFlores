package org.example;

public class Cheese extends Topping{
    double price;
    public Cheese(String name, int sandwichSize) {
        super(name, sandwichSize);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package org.example;

public class Meat extends Topping{
    double price;
    public Meat(String name, int sandwichSize) {
        super(name, sandwichSize);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

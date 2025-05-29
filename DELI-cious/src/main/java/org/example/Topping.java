package org.example;

public abstract class Topping {
    private String name;
    private int sandwichSize;

    public Topping(String name , int sandwichSize) {
        this.name = name;
        this.sandwichSize = sandwichSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSandwichSize() {
        return sandwichSize;
    }

    public void setSandwichSize(int sandwichSize) {
        this.sandwichSize = sandwichSize;
    }
}

package org.example;

import java.util.ArrayList;

public class Sandwich {
    private int size;
    private BreadType breadType;
    private ArrayList<Topping> toppings;
    private boolean isToasted;


    public Sandwich(int size, BreadType breadType, boolean isToasted) {
        this.size = size;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    @Override
    public String toString() {      //TODO List toppings here!!
        return "Sandwich:" +
                "size = " + size +
                ", breadType = " + breadType +
                ", isToasted = " + isToasted;
    }
}

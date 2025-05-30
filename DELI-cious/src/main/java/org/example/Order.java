package org.example;

import java.util.ArrayList;

public class Order {
    private String name;
    private double totalPrice;
    private ArrayList<Sandwich> sandwiches;
    private ArrayList<Drink> drinks;
    private ArrayList<Chip> chips;

    public Order(String name) {
        this.name = name;
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {

        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(ArrayList<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    public ArrayList<Chip> getChips() {
        return chips;
    }

    public void setChips(ArrayList<Chip> chips) {
        this.chips = chips;
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
        if (drink.getSize() == 4) {
            totalPrice += 2.00;
            drink.setPrice(2.00);
        } else if (drink.getSize() == 8) {
            totalPrice += 2.50;
            drink.setPrice(2.50);
        } else if (drink.getSize() == 12) {
            totalPrice += 3.00;
            drink.setPrice(3.00);
        }
    }

    public void addChip(Chip chip) {
        chips.add(chip);
        totalPrice += 1.50;
        chip.setPrice(1.50);
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        if (sandwich.getSize() == 4) {
            totalPrice += 5.50;
            sandwich.setPrice(5.50);
        } else if (sandwich.getSize() == 8) {
            totalPrice += 7.00;
            sandwich.setPrice(7.00);
        } else if (sandwich.getSize() == 12) {
            totalPrice += 8.50;
            sandwich.setPrice(8.50);
        }
    }

    public void addTopping(Sandwich sandwich, Topping topping) {
        sandwich.getToppings().add(topping);
        long meatCounter = sandwich.getToppings().stream()
                .filter(topping1 -> topping1 instanceof Meat).count();

        long cheeseCounter = sandwich.getToppings().stream()
                .filter(topping1 -> topping1 instanceof Cheese).count();
        if(topping instanceof Meat) {
            if (sandwich.getSize() == 4 && meatCounter == 1) {
                totalPrice += 1.00;
                ((Meat) topping).setPrice(1.00);

            } else if (sandwich.getSize() == 4 && meatCounter > 1) {
                totalPrice += .50;
                ((Meat) topping).setPrice(.50);

            } else if (sandwich.getSize() == 8 && meatCounter == 1) {
                totalPrice += 2.00;
                ((Meat) topping).setPrice(2.00);
            } else if (sandwich.getSize() == 8 && meatCounter > 1) {
                totalPrice += 1.00;
                ((Meat) topping).setPrice(1.00);
            } else if (sandwich.getSize() == 12 && meatCounter == 1) {
                totalPrice += 3.00;
                ((Meat) topping).setPrice(3.00);

            } else if (sandwich.getSize() == 12 && meatCounter > 1) {
                totalPrice += 1.50;
                ((Meat) topping).setPrice(1.50);
            }
        }
        if(topping instanceof Cheese){
            if (sandwich.getSize() == 4 && cheeseCounter == 1) {
                totalPrice += .75;
                ((Cheese) topping).setPrice(.75);
            }
            else if (sandwich.getSize() == 4 && cheeseCounter > 1) {
                totalPrice += .30;
                ((Cheese) topping).setPrice(.30);
            }
            else if (sandwich.getSize() == 8 && cheeseCounter == 1) {
                totalPrice += 1.50;
                ((Cheese) topping).setPrice(1.50);
            }
            else if (sandwich.getSize() == 8 && cheeseCounter > 1) {
                totalPrice += .60;
                ((Cheese) topping).setPrice(.60);
            }
            else if (sandwich.getSize() == 12 && cheeseCounter == 1) {
                totalPrice += 1.50;
                ((Cheese) topping).setPrice(1.50);
            }
            else if (sandwich.getSize() == 12 && cheeseCounter > 1) {
                totalPrice += .90;
                ((Cheese) topping).setPrice(.90);
            }
        }
    }

    public void subtractTotalPrice(double price){
        this.totalPrice -= price;
    }

}




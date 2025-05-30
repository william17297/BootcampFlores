package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {
    public void display() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n\nDELI-cious");
                System.out.println("~~~~~~~~~~~~");
                System.out.println("1.) New Order");
                System.out.println("0.) Exit");
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        order(scanner);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("\n\nPlease select 1.) or 2.)\n\n");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPlease select 1.) or 2.)\n\n");
            }
        }
    }

    public void order(Scanner scanner) {

        System.out.println("What is your name?");
        String name = scanner.nextLine();
        Order order = new Order(name);
        boolean notCancelled = true;
        while (notCancelled) {
            try {
                System.out.println("1.) Add Sandwich");
                System.out.println("2.) Add Drink");
                System.out.println("3.) Add Chips");
                System.out.println("4.) Checkout / remove item");
                System.out.println("0.) Cancel Order");
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        addSandwich(scanner, order);
                        break;
                    case 2:
                        addDrink(scanner, order);
                        break;
                    case 3:
                        addChip(scanner, order);
                        break;
                    case 4:

                        notCancelled = checkOut(scanner, order);

                        break;
                    case 0:
                        order.getSandwiches().clear();
                        order.getChips().clear();
                        order.getDrinks().clear();
                        notCancelled = false;
                        break;
                    default:
                        System.out.println("\n\nPlease select a number between 1 and 4 or 0 to cancel order.\n\n");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPlease select a number.\n\n");
            }
        }
    }

    public void addSandwich(Scanner scanner, Order order) {
        BreadType breadType = null;
        int breadSize = 0;
        while (true) {
            try {
                System.out.println("What type of bread would you like? \n" +
                        "1.) white\n" +
                        "2.) wheat\n" +
                        "3.) rye\n" +
                        "4.) wrap");
                int breadChoice = Integer.parseInt(scanner.nextLine());

                if (breadChoice == 1) {
                    breadType = BreadType.white;
                    break;
                } else if (breadChoice == 2) {
                    breadType = BreadType.wheat;
                    break;
                } else if (breadChoice == 3) {
                    breadType = BreadType.rye;
                    break;
                } else if (breadChoice == 4) {
                    breadType = BreadType.wrap;
                    break;
                } else {
                    System.out.println("\n\n\n\nPlease select 1 through 4");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\n\n\nPlease select 1 through 4");
            }
        }
        while (true) {
            try {
                System.out.println("What size? 1.)4\" 2.)8\" 3.)12\"");
                int breadSizeChoice = Integer.parseInt(scanner.nextLine());
                if (breadSizeChoice == 1) {
                    breadSize = 4;
                    break;
                } else if (breadSizeChoice == 2) {
                    breadSize = 8;
                    break;
                } else if (breadSizeChoice == 3) {
                    breadSize = 12;
                    break;
                } else {
                    System.out.println("\n\nPlease pick 1 through 3.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPlease enter a number.");
            }
        }
        while (true) {
            try {
                System.out.println("Do you want your bread toasted? 1.) Yes 2.) No");
                int breadToastedChoice = Integer.parseInt(scanner.nextLine());
                if (breadToastedChoice == 1) {
                    order.addSandwich(new Sandwich(breadSize, breadType, true));
                    break;
                } else if (breadToastedChoice == 2) {
                    order.addSandwich(new Sandwich(breadSize, breadType, false));
                    break;
                } else {
                    System.out.println("\n\nPlease pick 1 or 2.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPlease pick 1 or 2.");
            }
        }
        while (true) {
            try {
                System.out.println("1.) Add Topping");
                System.out.println("2.) Go Back");
                int wantsTopping = Integer.parseInt(scanner.nextLine());
                if (wantsTopping == 1) {
                    addTopping(scanner, order, order.getSandwiches().get(order.getSandwiches().size() - 1));
                } else if (wantsTopping == 2) {
                    break;
                } else {
                    System.out.println("\n\nPlease type 1 or 2.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPlease type 1 or 2.");
            }
        }
    }

    public void addTopping(Scanner scanner, Order order, Sandwich sandwich) {
        boolean hasNotPicked = true;
        boolean pickedWrong = false;
        while (hasNotPicked) {
            try {
                if (pickedWrong) {
                    System.out.println("\n\nPlease select 1 through 6.\n\n");
                    pickedWrong = false;
                }

                System.out.println("1.) Add meat");
                System.out.println("2.) Add cheese");
                System.out.println("3.) Add other toppings");
                System.out.println("4.) Select sauces");
                System.out.println("5.) Sides");
                System.out.println("6.) Go back");
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        while (true) {
                            try {
                                System.out.println("Meats\n" +
                                        "1.) steak\n" +
                                        "2.) ham\n" +
                                        "3.) salami\n" +
                                        "4.) roast beef\n" +
                                        "5.) chicken\n" +
                                        "6.) bacon");
                                int meatSelected = Integer.parseInt(scanner.nextLine());
                                if (meatSelected == 1) {
                                    order.addTopping(sandwich, new Meat("steak", sandwich.getSize()));
                                    break;
                                } else if (meatSelected == 2) {
                                    order.addTopping(sandwich, new Meat("ham", sandwich.getSize()));
                                    break;
                                } else if (meatSelected == 3) {
                                    order.addTopping(sandwich, new Meat("salami", sandwich.getSize()));
                                    break;
                                } else if (meatSelected == 4) {
                                    order.addTopping(sandwich, new Meat("roast beef", sandwich.getSize()));
                                    break;
                                } else if (meatSelected == 5) {
                                    order.addTopping(sandwich, new Meat("chicken", sandwich.getSize()));
                                    break;
                                } else if (meatSelected == 6) {
                                    order.addTopping(sandwich, new Meat("bacon", sandwich.getSize()));
                                    break;
                                } else {
                                    System.out.println("\n\nPlease select 1 through 6.\n\n");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("\n\nPlease select 1 through 6.\n\n");
                            }
                        }
                        break;
                    case 2:
                        while (true) {
                            try {
                                System.out.println("Cheese\n" +
                                        "1.) american\n" +
                                        "2.) provolone\n" +
                                        "3.) cheddar\n" +
                                        "4.) swiss");
                                int cheeseSelected = Integer.parseInt(scanner.nextLine());
                                if (cheeseSelected == 1) {
                                    order.addTopping(sandwich, new Cheese("american", sandwich.getSize()));
                                    break;
                                } else if (cheeseSelected == 2) {
                                    order.addTopping(sandwich, new Cheese("provolone", sandwich.getSize()));
                                    break;
                                } else if (cheeseSelected == 3) {
                                    order.addTopping(sandwich, new Cheese("cheddar", sandwich.getSize()));
                                    break;
                                } else if (cheeseSelected == 4) {
                                    order.addTopping(sandwich, new Cheese("swiss", sandwich.getSize()));
                                    break;
                                } else {
                                    System.out.println("\n\nPlease select 1 through 4.\n\n");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("\n\nPlease select 1 through 4.\n\n");
                            }
                        }
                        break;
                    case 3:
                        while (true) {
                            try {
                                System.out.println("Regular Toppings\n" +
                                        "1.) lettuce\n" +
                                        "2.) peppers\n" +
                                        "3.) onions\n" +
                                        "4.) tomatoes\n" +
                                        "5.) jalapeños\n" +
                                        "6.) cucumbers\n" +
                                        "7.) pickles\n" +
                                        "8.) guacamole\n" +
                                        "9.) mushrooms");
                                int regularToppingSelected = Integer.parseInt(scanner.nextLine());
                                if (regularToppingSelected == 1) {
                                    order.addTopping(sandwich, new RegularTopping("lettuce", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 2) {
                                    order.addTopping(sandwich, new RegularTopping("peppers", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 3) {
                                    order.addTopping(sandwich, new RegularTopping("onions", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 4) {
                                    order.addTopping(sandwich, new RegularTopping("tomatoes", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 5) {
                                    order.addTopping(sandwich, new RegularTopping("jalapeños", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 6) {
                                    order.addTopping(sandwich, new RegularTopping("cucumbers", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 7) {
                                    order.addTopping(sandwich, new RegularTopping("pickles", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 8) {
                                    order.addTopping(sandwich, new RegularTopping("guacamole", sandwich.getSize()));
                                    break;
                                } else if (regularToppingSelected == 9) {
                                    order.addTopping(sandwich, new RegularTopping("mushrooms", sandwich.getSize()));
                                    break;
                                } else {
                                    System.out.println("\n\nPlease select 1 through 9\n\n");
                                }
                            } catch (NumberFormatException ex) {

                                System.out.println("\n\nPlease select 1 through 9\n\n");
                            }
                        }
                        break;
                    case 4:
                        while (true) {
                            try {
                                System.out.println("Sauces\n" +
                                        "1.) mayo\n" +
                                        "2.) mustard\n" +
                                        "3.) ketchup\n" +
                                        "4.) ranch\n" +
                                        "5.) thousand islands\n" +
                                        "6.) vinaigrette");
                                int sauce = Integer.parseInt(scanner.nextLine());
                                if (sauce == 1) {
                                    order.addTopping(sandwich, new Sauce("mayo", sandwich.getSize()));
                                    break;
                                } else if (sauce == 2) {
                                    order.addTopping(sandwich, new Sauce("mustard", sandwich.getSize()));
                                    break;
                                } else if (sauce == 3) {
                                    order.addTopping(sandwich, new Sauce("ketchup", sandwich.getSize()));
                                    break;
                                } else if (sauce == 4) {
                                    order.addTopping(sandwich, new Sauce("ranch", sandwich.getSize()));
                                    break;
                                } else if (sauce == 5) {
                                    order.addTopping(sandwich, new Sauce("thousand islands", sandwich.getSize()));
                                    break;
                                } else if (sauce == 6) {
                                    order.addTopping(sandwich, new Sauce("vinaigrette", sandwich.getSize()));
                                    break;
                                } else {
                                    System.out.println("\n\nEnter a number from 1 through 6.\n\n");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("\n\nEnter a number from 1 through 6.\n\n");
                            }
                        }
                        break;
                    case 5:
                        while (true) {
                            try {
                                System.out.println("Sides\n" +
                                        "1.) au jus\n" +
                                        "2.) sauce");
                                int sides = Integer.parseInt(scanner.nextLine());
                                if (sides == 1) {
                                    order.addTopping(sandwich, new Sides("au jus", sandwich.getSize()));
                                    break;
                                } else if (sides == 2) {
                                    order.addTopping(sandwich, new Sides("sauce", sandwich.getSize()));
                                    break;
                                } else {
                                    System.out.println("\n\n Enter a number from 1 through 2.\n\n");
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println("\n\n Enter a number from 1 through 2.\n\n");
                            }
                        }
                        break;
                    case 6:
                        hasNotPicked = false;
                    default:
                        pickedWrong = true;
                }
            } catch (NumberFormatException ex) {
                pickedWrong = true;
            }
        }
    }

    public void addDrink(Scanner scanner, Order order) {
        System.out.println("What drink would you like?");
        String name = scanner.nextLine();
        while (true) {
            try {
                System.out.println("What size? 1.) small 2.) medium 3.) large");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    order.addDrink(new Drink(name, 4));
                    break;
                } else if (choice == 2) {
                    order.addDrink(new Drink(name, 8));
                    break;
                } else if (choice == 3) {
                    order.addDrink(new Drink(name, 12));
                    break;
                } else {
                    System.out.println("Pick 1 through 3");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Pick 1 through 3");
            }
        }
    }

    public void addChip(Scanner scanner, Order order) {
        System.out.println("What chip would you like?");
        order.addChip(new Chip(scanner.nextLine()));
    }

    public boolean checkOut(Scanner scanner, Order order) {
        while (true) {
            try {
                displayItems(order);
                System.out.println("1.) Confirm 2.) Remove Item 3.) Go back");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    OrderFileManager orderFileManager = new OrderFileManager();
                    orderFileManager.saveOrder(order);
                    return false;
                } else if (choice == 2) {
                    removeItems(scanner, order);
                    return true;
                } else if (choice == 3) {
                    return true;
                } else {
                    System.out.println("Please select 1 through 3.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please select 1 through 3.");
            }
        }
    }

    public void displayItems(Order order) {
        System.out.println("Order for " + order.getName() + ": ");
        order.getSandwiches().stream().forEach(sandwich -> {
            if(sandwich.getSize() == 4) {
                System.out.println("Sandwhich " + (order.getSandwiches().indexOf(sandwich) + 1) + ": bread size price - $5.50, " + sandwich.toString());
            }
            else if(sandwich.getSize() == 8) {
                System.out.println("Sandwhich " + (order.getSandwiches().indexOf(sandwich) + 1) + ": bread size price - $7.00, " + sandwich.toString());
            }else if(sandwich.getSize() == 12) {
                System.out.println("Sandwhich " + (order.getSandwiches().indexOf(sandwich) + 1) + ": bread size price - $8.50, " + sandwich.toString());
            }
            if (!sandwich.getToppings().isEmpty()) {
                System.out.println("   Toppings:");
            }
            sandwich.getToppings().stream()
                    .sorted(Comparator.comparing(topping -> topping.getName()))
                    .collect(Collectors.toList()).forEach(topping -> {
                        if (topping instanceof Cheese) {
                            System.out.println("     " + topping.getName() + " Cheese");
                        } else {
                            System.out.println("     " + topping.getName());
                        }
                    });
        });
        if (!order.getDrinks().isEmpty()) {
            System.out.println("Drinks:");
        }
        order.getDrinks().stream().forEach(drink -> {
            if (drink.getSize() == 4) {
                System.out.println("   " + drink.getName() + ": size - S ,  Price: $2.00");
            }
            if (drink.getSize() == 8) {
                System.out.println("   " + drink.getName() + ":  size - M , Price: $2.50");
            }
            if (drink.getSize() == 12) {
                System.out.println("   " + drink.getName() + ": size - L , Price: $3.00");
            }
        });
        if (!order.getChips().isEmpty()) {
            System.out.println("Chips: ");
        }
        order.getChips().stream().forEach(chip -> System.out.println("   " + chip.getName() + ": Price: $1.50"));
        System.out.printf("\n\nTotal Price: $%.2f%n\n\n", order.getTotalPrice());

    }

    public void removeItems(Scanner scanner, Order order) {
        Boolean hasNotSelected = true;
        while (hasNotSelected) {
            try {
                System.out.println("1.) Remove Toppings");
                System.out.println("2.) Remove Drinks");
                System.out.println("3.) Remove Chips");
                System.out.println("4.) Go back");
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
                        removeToppingFromSandwich(scanner , order);
                        break;
                    case 2:
                        removeDrinkFromOrder(scanner, order);
                        break;
                    case 3:
                        removeChipsFromOrder(scanner , order);
                        break;
                    case 4:
                        hasNotSelected = false;
                        break;
                    default:
                        System.out.println("\n\nPlease pick 1 through 4.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPlease pick 1 through 4.");
            }
        }
    }

    private void removeToppingFromSandwich(Scanner scanner , Order order) {

        if (order.getSandwiches().isEmpty()) {
            System.out.println("No sandwiches available.");
            return;
        }

        System.out.println("\n   Select a Sandwich to Remove Topping From    ");
        for (int i = 0; i < order.getSandwiches().size(); i++) {
            System.out.println((i + 1) + ".) Sandwich " + (i + 1));
        }

        System.out.print("Enter the number of the sandwich or 0 to cancel: ");
        try {
            int sandwichChoice = Integer.parseInt(scanner.nextLine());
            if (sandwichChoice == 0) return;
            if (sandwichChoice < 1 || sandwichChoice > order.getSandwiches().size()) {
                System.out.println("Invalid sandwich choice.");
                return;
            }

            Sandwich selectedSandwich = order.getSandwiches().get(sandwichChoice - 1);
            List<Topping> toppings = selectedSandwich.getToppings();

            if (toppings.isEmpty()) {
                System.out.println("Sandwich " + sandwichChoice + " has no toppings.");
                return;
            }

            System.out.println("\n    Toppings on Sandwich " + sandwichChoice + "    ");
            for (int i = 0; i < toppings.size(); i++) {
                System.out.println((i + 1) + ".) " + toppings.get(i).getName());
            }

            System.out.print("Enter number of toppings to remove or press 0 to exit: ");
            int toppingChoice = Integer.parseInt(scanner.nextLine());
            if (toppingChoice == 0) return;
            if (toppingChoice < 1 || toppingChoice > toppings.size()) {
                System.out.println("Invalid topping choice.");
                return;
            }

            //Removes topping
            Topping removed = toppings.remove(toppingChoice - 1);
            if(removed instanceof Meat){
               order.subtractTotalPrice(((Meat) removed).getPrice());
            }else if(removed instanceof Cheese){
               order.subtractTotalPrice(((Cheese) removed).getPrice());
            }
            System.out.println(removed.getName() + " removed from Sandwich " + sandwichChoice + ".");

        } catch (NumberFormatException ex) {
            System.out.println("Please enter a number.");
        }
    }

    private void removeDrinkFromOrder(Scanner scanner, Order order) {

        if (order.getDrinks().isEmpty()) {
            System.out.println("No drinks to remove.");
            return;
        }

        System.out.println("\n   Drinks    ");
        for (int i = 0; i < order.getDrinks().size(); i++) {
            System.out.println((i + 1) + ".) " + order.getDrinks().get(i).getName());
        }

        System.out.print("Enter the number of drink or press 0 to go back: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) return;
            if (choice < 1 || choice > order.getDrinks().size()) {
                System.out.println("Invalid choice.");
            } else {
                Drink removed = order.getDrinks().remove(choice - 1);
                order.subtractTotalPrice(removed.getPrice());
                System.out.println(order.getDrinks().get(choice - 1).getName() + " removed from order.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Please type a number.");
        }
    }

    private void removeChipsFromOrder(Scanner scanner, Order order) {

        if (order.getChips().isEmpty()) {
            System.out.println("No chips to remove.");
            return;
        }

        System.out.println("\n   Chips    ");
        for (int i = 0; i < order.getChips().size(); i++) {
            System.out.println((i + 1) + ".) " + order.getChips().get(i).getName());
        }

        System.out.print("Enter the number of chip or press 0 to go back: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) return;
            if (choice < 1 || choice > order.getChips().size()) {
                System.out.println("Invalid choice.");
            } else {
                Chip removed = order.getChips().remove(choice - 1);
                order.subtractTotalPrice(removed.getPrice());
                System.out.println(removed.getName() + " removed from order.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Please type a number.");
        }
    }
}

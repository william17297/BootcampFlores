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
        while (true) {
            try {
                System.out.println("1.) Add Sandwich");
                System.out.println("2.) Add Drink");
                System.out.println("3.) Add Chips");
                System.out.println("4.) Checkout");
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
                        checkOut(scanner, order);
                        break;
                    case 0:
                        //TODO _________________________________wgeegw_______________rerwer____________________wegwegweg____________________
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
        while(true) {
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
                }
                else{
                    System.out.println("\n\nPlease pick 1 through 3.");
                }
            }
            catch (NumberFormatException ex){
                System.out.println("\n\nPlease enter a number.");
            }
        }
        while(true) {
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
            }
            catch (NumberFormatException ex){
                System.out.println("\n\nPlease pick 1 or 2.");
            }
        }
        while(true) {
            try {
                System.out.println("1.) Add Topping");
                System.out.println("2.) Go Back");
                int wantsTopping = Integer.parseInt(scanner.nextLine());
                if (wantsTopping == 1) {
                    addTopping(scanner, order, order.getSandwiches().get(order.getSandwiches().size() - 1));
                }
                else if (wantsTopping == 2) {
                    break;
                }
                else {
                    System.out.println("\n\nPlease type 1 or 2.");
                }
            }
            catch (NumberFormatException ex){
                System.out.println("\n\nPlease type 1 or 2.");
            }
        }
    }

    public void addTopping(Scanner scanner, Order order, Sandwich sandwich) {
        boolean hasNotPicked = true;
        while (hasNotPicked) {
            try {
                System.out.println("1.) Add meat");
                System.out.println("2.) Add cheese");
                System.out.println("3.) Add other toppings");
                System.out.println("4.) Select sauces");
                System.out.println("5.) Sides");
                System.out.println("6.) Go back");
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1:
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
                        } else if (meatSelected == 2) {
                            order.addTopping(sandwich, new Meat("ham", sandwich.getSize()));
                        } else if (meatSelected == 3) {
                            order.addTopping(sandwich, new Meat("salami", sandwich.getSize()));
                        } else if (meatSelected == 4) {
                            order.addTopping(sandwich, new Meat("roast beef", sandwich.getSize()));
                        } else if (meatSelected == 5) {
                            order.addTopping(sandwich, new Meat("chicken", sandwich.getSize()));
                        } else if (meatSelected == 6) {
                            order.addTopping(sandwich, new Meat("bacon", sandwich.getSize()));
                        }
                        break;
                    case 2:
                        System.out.println("Cheese\n" +
                                "1.) american\n" +
                                "2.) provolone\n" +
                                "3.) cheddar\n" +
                                "4.) swiss");
                        int cheeseSelected = Integer.parseInt(scanner.nextLine());
                        if (cheeseSelected == 1) {
                            order.addTopping(sandwich, new Cheese("american", sandwich.getSize()));
                        } else if (cheeseSelected == 2) {
                            order.addTopping(sandwich, new Cheese("provolone", sandwich.getSize()));
                        } else if (cheeseSelected == 3) {
                            order.addTopping(sandwich, new Cheese("cheddar", sandwich.getSize()));
                        } else if (cheeseSelected == 4) {
                            order.addTopping(sandwich, new Cheese("swiss", sandwich.getSize()));
                        }
                        break;
                    case 3:
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
                        } else if (regularToppingSelected == 2) {
                            order.addTopping(sandwich, new RegularTopping("peppers", sandwich.getSize()));
                        } else if (regularToppingSelected == 3) {
                            order.addTopping(sandwich, new RegularTopping("onions", sandwich.getSize()));
                        } else if (regularToppingSelected == 4) {
                            order.addTopping(sandwich, new RegularTopping("tomatoes", sandwich.getSize()));
                        } else if (regularToppingSelected == 5) {
                            order.addTopping(sandwich, new RegularTopping("jalapeños", sandwich.getSize()));
                        } else if (regularToppingSelected == 6) {
                            order.addTopping(sandwich, new RegularTopping("cucumbers", sandwich.getSize()));
                        } else if (regularToppingSelected == 7) {
                            order.addTopping(sandwich, new RegularTopping("pickles", sandwich.getSize()));
                        } else if (regularToppingSelected == 8) {
                            order.addTopping(sandwich, new RegularTopping("guacamole", sandwich.getSize()));
                        } else if (regularToppingSelected == 9) {
                            order.addTopping(sandwich, new RegularTopping("mushrooms", sandwich.getSize()));
                        }
                        break;
                    case 4:
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
                        } else if (sauce == 2) {
                            order.addTopping(sandwich, new Sauce("mustard", sandwich.getSize()));
                        } else if (sauce == 3) {
                            order.addTopping(sandwich, new Sauce("ketchup", sandwich.getSize()));
                        } else if (sauce == 4) {
                            order.addTopping(sandwich, new Sauce("ranch", sandwich.getSize()));
                        } else if (sauce == 5) {
                            order.addTopping(sandwich, new Sauce("thousand islands", sandwich.getSize()));
                        } else if (sauce == 6) {
                            order.addTopping(sandwich, new Sauce("vinaigrette", sandwich.getSize()));
                        }
                        break;
                    case 5:
                        System.out.println("Sides\n" +
                                "1.) au jus\n" +
                                "2.) sauce");
                        int sides = Integer.parseInt(scanner.nextLine());
                        if (sides == 1) {
                            order.addTopping(sandwich, new Sides("au jus", sandwich.getSize()));
                        } else if (sides == 2) {
                            order.addTopping(sandwich, new Sides("sauce", sandwich.getSize()));
                        }
                        break;
                    case 6:
                        hasNotPicked = false;
                    default:
                        System.out.println("\n\nPlease select 1 through 6.\n\n");
                }
            }
            catch (NumberFormatException ex){
                System.out.println("\n\nPlease select 1 through 6.\n\n");
            }
        }
    }

    public void addDrink(Scanner scanner, Order order) {
        System.out.println("What drink would you like?");
        String name = scanner.nextLine();
        System.out.println("What size? 1.) small 2.) medium 3.) large");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            order.addDrink(new Drink(name, 4));
        }
        if (choice == 2) {
            order.addDrink(new Drink(name, 8));
        }
        if (choice == 3) {
            order.addDrink(new Drink(name, 12));
        }
    }

    public void addChip(Scanner scanner, Order order) {
        System.out.println("What chip would you like?");
        order.addChip(new Chip(scanner.nextLine()));
    }

    public void checkOut(Scanner scanner, Order order) {
        displayItems(order);
        System.out.println("1.) Confirm 2.) Go back");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            OrderFileManager orderFileManager = new OrderFileManager();
            orderFileManager.saveOrder(order);
        } else if (choice == 2) {
            //TODO--------------------------------
        }
    }

    public void displayItems(Order order) {
        System.out.println("Order for " + order.getName() + ": ");
        order.getSandwiches().stream().forEach(sandwich -> {
            System.out.println(sandwich.toString());
            sandwich.getToppings().stream()
                    .sorted(Comparator.comparing(topping -> topping.getName()))
                    .collect(Collectors.toList()).forEach(topping -> {
                        if (topping instanceof Cheese) {
                            System.out.println(topping.getName() + " Cheese");
                        } else {
                            System.out.println(topping.getName());
                        }
                    });
        });
        System.out.println(order.getTotalPrice());

    }
}

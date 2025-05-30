package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OrderFileManager {
    public void saveOrder(Order order) {

        String filePath = "src/main/resources/receipts/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
        File file = new File(filePath);

        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            writer.write("Thank you for your purchase " + order.getName() + "!\n");
            writer.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            if (!order.getSandwiches().isEmpty()) {
                order.getSandwiches().stream().forEach(sandwich -> {
                    try {
                        if(sandwich.getSize() == 4){
                        writer.write("Sandwich " + (order.getSandwiches().indexOf(sandwich) + 1) + ": bread size price - $5.50, " + sandwich.toString() + "\n");
                        }if(sandwich.getSize() == 8){
                        writer.write("Sandwich " + (order.getSandwiches().indexOf(sandwich) + 1) + ": bread size price - $7.00, " + sandwich.toString() + "\n");
                        }if(sandwich.getSize() == 12){
                        writer.write("Sandwich " + (order.getSandwiches().indexOf(sandwich) + 1) + ": bread size price - $8.50, " + sandwich.toString() + "\n");
                        }
                        //writer.write(sandwich.getToppings().toString());
                        if (!sandwich.getToppings().isEmpty()) {
                            writer.write("   Toppings:\n");
                        }
                        sandwich.getToppings().stream()
                                .sorted(Comparator.comparing(topping -> topping.getName()))
                                .collect(Collectors.toList()).forEach(topping -> {
                                    if (topping instanceof Cheese) {
                                        try {
                                            writer.write(String.format("     %s Cheese: price - $%.2f%n", topping.getName(), ((Cheese) topping).getPrice()));
                                            //writer.write("     " + topping.getName() + " Cheese: price - $" + ((Cheese) topping).getPrice() + "\n");
                                            //writer.write(String.format("\n\nTotal Price: $%.2f%n\n\n", ((Cheese) topping).getPrice()));
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    } else if (topping instanceof Meat) {
                                        try {
                                            writer.write(String.format("     %s: price - $%.2f%n", topping.getName(), ((Meat) topping).getPrice()));
                                            // writer.write("     " + topping.getName() + ": price - $" + ((Meat) topping).getPrice() + "\n");
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }

                                    } else {
                                        try {
                                            writer.write("     " + topping.getName() + ": price - Free \n");
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            if (!order.getDrinks().isEmpty()) {
                writer.write("Drinks: \n");
            }
            order.getDrinks().stream().forEach(drink -> {
                if (drink.getSize() == 4) {
                    try {
                        writer.write("   " + drink.getName() + ": size - S ,  Price: $2.00\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (drink.getSize() == 8) {
                    try {
                        writer.write("   " + drink.getName() + ":  size - M , Price: $2.50\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (drink.getSize() == 12) {
                    try {
                        writer.write("   " + drink.getName() + ": size - L , Price: $3.00\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            if (!order.getChips().isEmpty()) {
                writer.write("Chips:  \n");
            }
            order.getChips().stream().forEach(chip -> {
                try {
                    writer.write("   " + chip.getName() + ": Price: $1.50\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.write(String.format("\n\nTotal Price: $%.2f%n\n\n", order.getTotalPrice()));
            writer.close();

        } catch (IOException e) {
            System.out.println("Failed to upload order  .");
            e.printStackTrace();
        }
    }
}

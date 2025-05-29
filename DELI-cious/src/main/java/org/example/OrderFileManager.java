package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                writer.write("Thank you for your purchase!\n");

                        writer.write(order.getName() + "\n");
                        order.getSandwiches().stream().forEach(sandwich -> {
                            try {
                                writer.write(sandwich.toString());
                                //writer.write(sandwich.getToppings().toString());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        writer.write(order.getDrinks() + "\n");
                        writer.write(order.getChips() + "\n");
                        writer.write(order.getTotalPrice() + "\n");


                /*for(int i = 0; i < order.getSandwiches().size(); i++){
                    writer.write(order.getSandwiches().get(i).toString() + "\n");

                }
                for(Sandwich sandwich : order.getSandwiches()){

                }*/
                writer.close();

        } catch (IOException e) {
            System.out.println("Failed to upload order  .");
            e.printStackTrace();
        }
    }
}

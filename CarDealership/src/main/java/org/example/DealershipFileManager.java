package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    public Dealership getDealerShip() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Dealership dealership;
            String[] row = bufferedReader.readLine().split("\\|");
            String name = row[0];
            String address = row[1];
            String phone = row[2];
            dealership = new Dealership(name, address, phone);

            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] row1 = input.split("\\|");
                int vin = Integer.parseInt(row1[0]);
                int year = Integer.parseInt(row1[1]);
                String make = row1[2];
                String model = row1[3];
                VehicleType vehicleType = VehicleType.valueOf(row1[4]);
                String color = row1[5];
                int odometer = Integer.parseInt(row1[6]);
                double price = Double.parseDouble(row1[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
            bufferedReader.close();
            return dealership;
        } catch (IOException ex) {
            System.out.println("Failed to load csv file.");
            return null;
        }
    }

    public void saveDealerShip(Dealership dealership , int option) {
        String filePath = "src/main/resources/inventory.csv";
        File file = new File(filePath);

        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;
            if(option == 1) {
                FileWriter writer = new FileWriter(file , true);
                Vehicle vehicle = dealership.getAllVehicles().get(dealership.getAllVehicles().size() - 1);
                writer.write(vehicle.toString() + "\n");
                writer.close();
            }
            else if(option == 2){
                FileWriter writer = new FileWriter(file);
                writer.write("D & B Used Cars|111 Old Benbrook Rd|817-555-5555\n");
                for(int i = 0; i < dealership.getAllVehicles().size(); i++){
                    Vehicle vehicle = dealership.getAllVehicles().get(i);
                    writer.write(vehicle.toString() + "\n");

                }
                writer.close();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong while saving the vehicle to the dealership.");
            e.printStackTrace();
        }
    }

}

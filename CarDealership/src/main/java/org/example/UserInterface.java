package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface{
    Dealership dealership;

    public void display() throws  InterruptedException{
        init();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n\n\n    ----------Dealership----------");
                System.out.println("1.) Find vehicles within a price range");
                System.out.println("2.) Find vehicles by make / model");
                System.out.println("3.) Find vehicles by year range");
                System.out.println("4.) Find vehicles by color");
                System.out.println("5.) Find vehicles by mileage range");
                System.out.println("6.) Find vehicles by type");
                System.out.println("7.) List ALL vehicles");
                System.out.println("8.) add a vehicle");
                System.out.println("9.) remove a vehicle");
                System.out.println("0.) Quit");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        processGetByPriceRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 2:
                        processGetByMakeModelRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 3:
                        processGetByYearRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 4:
                        processGetByColorRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 5:
                        processGetByMileageRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 6:
                        processGetByVehicleTypeRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 7:
                        processGetAllVehiclesRequest();
                        Thread.sleep(1000);
                        break;

                    case 8:
                        processAddVehicleRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 9:
                        processRemoveVehicleRequest(scanner);
                        Thread.sleep(1000);
                        break;

                    case 0:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\n\nPLEASE ENTER A NUMBER FROM 1 THROUGH 9 OR 0 TO EXIT");
                        Thread.sleep(1000);
                }
            } catch (NumberFormatException ex) {
                System.out.println("\n\nPLEASE ENTER A NUMBER FROM 1 THROUGH 9 OR 0 TO EXIT");
                Thread.sleep(1000);
            }
        }

    }


    private void processGetByPriceRequest(Scanner scanner) {
        double min;
        double max;
        while (true) {
            try {
                System.out.print("Enter minimum amount:");
                min = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("ENTER A NUMBER");
            }
        }
        while (true) {
            try {
                System.out.print("Enter maximum amount:");
                max = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                System.out.println("ENTER A NUMBER");
            }
        }
        displayVehicles(dealership.getVehicleByPrice(min, max));
        if(dealership.getVehicleByPrice(min, max).isEmpty()){
            System.out.println("\n\n\n\nCould not find a vehicle within that price range.");
        }
    }

    private void processGetByMakeModelRequest(Scanner scanner) {
        System.out.print("Enter maker:");
        String maker = scanner.nextLine();
        System.out.print("Enter model:");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehicleByMakeModel(maker, model));
        if(dealership.getVehicleByMakeModel(maker, model).isEmpty()){
            System.out.println("\n\n\n\nCould not find a vehicle with that make and model.");
        }
    }

    private void processGetByYearRequest(Scanner scanner) {
        int year;
        int year2;
        while(true) {
            try {
                System.out.println("Enter Years from:");
                year = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter the Year in its length as a number (Ex. 2012)");
            }
        }
        while(true){
            try {
                System.out.println("to:");
                year2 = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter the Year in its length as a number (Ex. 2012)");
            }
        }
        displayVehicles(dealership.getVehicleByYear(year, year2));
        if(dealership.getVehicleByYear(year, year2).isEmpty()){
            System.out.println("\n\n\n\nCould not find a vehicle within that year range.");
        }


    }

    private void processGetByColorRequest(Scanner scanner) {
        System.out.println("Enter color:");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehicleByColor(color));
        if(dealership.getVehicleByColor(color).isEmpty()){
            System.out.println("\n\n\n\nCould not find a vehicle with that color.");
        }
    }

    private void processGetByMileageRequest(Scanner scanner) {
        int mileageMin;
        int mileageMax;

        while(true){
            try {
                System.out.println("Enter the minimum mileage: ");
                mileageMin = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Enter a number.");
            }
        }
        while(true) {
            try {
                System.out.println("Enter the maximum mileage: ");
                mileageMax = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Enter a number.");
            }
        }
        displayVehicles(dealership.getVehicleByMileage(mileageMin, mileageMax));
        if(dealership.getVehicleByMileage(mileageMin, mileageMax).isEmpty()){
            System.out.println("\n\n\n\nCould not find a vehicle within that mileage range.");
        }

    }

    private void processGetByVehicleTypeRequest(Scanner scanner) {
        while(true){
        try {
            System.out.println("Enter your vehicle type.");
            VehicleType vehicleType = VehicleType.valueOf(scanner.nextLine());
            displayVehicles(dealership.getVehicleByType(vehicleType));
            if(dealership.getVehicleByType(vehicleType).isEmpty()){
                System.out.println("\n\n\n\nSorry, we do not currently have a vehicle with that type.");
            }
            break;
        }
        catch (IllegalArgumentException ex){
            System.out.println(" search by one of these Vehicle types:\n" +
                    "    SUV,\n" +
                    "    truck,\n" +
                    "    sedan,\n" +
                    "    Impala,\n" +
                    "    wagon");
        }
        }
    }

    private void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
        if(dealership.getAllVehicles().isEmpty()){
            System.out.println("\n\n\n\nSorry, we currently have no cars available.");
        }
    }

    private void processAddVehicleRequest(Scanner scanner) {
        int vin;
        int year;
        int odometer;
        double price;
        VehicleType vehicleType;
        while(true) {
            try{
            System.out.print("Enter vin: ");
            vin = Integer.parseInt(scanner.nextLine());
            break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }
        while(true){
            try{
                System.out.print("Enter year: ");
                year = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }
        System.out.print("Enter make: ");
        String makeVehicle = scanner.nextLine();
        System.out.print("Enter model: ");
        String modelVehicle = scanner.nextLine();
        while(true) {
            try {
                System.out.print("Enter type: ");
                vehicleType = VehicleType.valueOf(scanner.nextLine());
                break;
            }
            catch (IllegalArgumentException ex){
                System.out.println(" Enter one of these Vehicle types:\n" +
                        "    SUV,\n" +
                        "    truck,\n" +
                        "    sedan,\n" +
                        "    Impala,\n" +
                        "    wagon");
            }
        }
        System.out.print("Enter color: ");
        String colorVehicle = scanner.nextLine();
        while(true) {
            try {
                System.out.print("Enter odometer: ");
                odometer = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }
        while(true) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Enter a number.");
            }
        }
        dealership.addVehicle(new Vehicle(vin, year, makeVehicle,
                modelVehicle, vehicleType, colorVehicle, odometer, price));
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealerShip(dealership, 1);
        System.out.println("\n\n\n\nVehicle added to inventory.");
    }

    private void processRemoveVehicleRequest(Scanner scanner) {
        int vin;
        int year;
        int odometer;
        double price;
        VehicleType vehicleType;
        while(true) {
            try{
                System.out.print("Enter vin: ");
                vin = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }
        while(true){
            try{
                System.out.print("Enter year: ");
                year = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }
        System.out.print("Enter make: ");
        String makeVehicle = scanner.nextLine();
        System.out.print("Enter model: ");
        String modelVehicle = scanner.nextLine();
        while(true) {
            try {
                System.out.print("Enter type: ");
                vehicleType = VehicleType.valueOf(scanner.nextLine());
                break;
            }
            catch (IllegalArgumentException ex){
                System.out.println(" Enter one of these Vehicle types:\n" +
                        "    SUV,\n" +
                        "    truck,\n" +
                        "    sedan,\n" +
                        "    Impala,\n" +
                        "    wagon");
            }
        }
        System.out.print("Enter color: ");
        String colorVehicle = scanner.nextLine();
        while(true) {
            try {
                System.out.print("Enter odometer: ");
                odometer = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a number.");
            }
        }
        while(true) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch (NumberFormatException ex){
                System.out.println("Enter a number.");
            }
        }
        dealership.removeVehicle(new Vehicle(vin, year, makeVehicle,
                modelVehicle, vehicleType, colorVehicle, odometer, price));
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        processGetAllVehiclesRequest();
        dealershipFileManager.saveDealerShip(dealership, 2);
        System.out.println("\n\n\n\nVehicle removed from inventory.");
    }

    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealerShip();

    }

    private void displayVehicles(List<Vehicle> vehicles) {  //Method will help list vehicles
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println(vehicles.get(i).toString());
        }
    }


}

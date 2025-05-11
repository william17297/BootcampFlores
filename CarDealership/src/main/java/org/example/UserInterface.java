package org.example;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership;

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Pick 1 through 9 or 0 to exit");
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
                    break;

                case 2:
                    processGetByMakeModelRequest(scanner);
                    break;

                case 3:
                    processGetByYearRequest(scanner);
                    break;

                case 4:
                    processGetByColorRequest(scanner);
                    break;

                case 5:
                    processGetByMileageRequest(scanner);
                    break;

                case 6:
                    processGetByVehicleTypeRequest(scanner);
                    break;

                case 7:
                    processGetAllVehiclesRequest();
                    break;

                case 8:
                    processAddVehicleRequest(scanner);
                    break;

                case 9:
                    processRemoveVehicleRequest(scanner);
                    break;

                case 10:
                    System.exit(0);
                    break;

                default:
                    System.out.println("PLEASE ENTER A NUMBER FROM 1 THROUGH 9 OR 0 TO EXIT");
            }
        }
    }

    private void processGetByPriceRequest(Scanner scanner) {
        System.out.print("Enter minimum amount:");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum amount:");
        double max = Double.parseDouble(scanner.nextLine());
        displayVehicles(dealership.getVehicleByPrice(min, max));

    }

    private void processGetByMakeModelRequest(Scanner scanner) {
        System.out.print("Enter maker:");
        String maker = scanner.nextLine();
        System.out.print("Enter model:");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehicleByMakeModel(maker, model));
    }

    private void processGetByYearRequest(Scanner scanner) {
        System.out.println("Enter Years from:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("to:");
        int year2 = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehicleByYear(year, year2));
    }

    private void processGetByColorRequest(Scanner scanner) {
        System.out.println("Enter color:");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehicleByColor(color));
    }

    private void processGetByMileageRequest(Scanner scanner) {
        System.out.println("Enter mileage:");
        int yearMin = Integer.parseInt(scanner.nextLine());
        int yearMax = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehicleByMileage(yearMin, yearMax));
    }

    private void processGetByVehicleTypeRequest(Scanner scanner) {
        System.out.println("Enter your vehicle type.");
        VehicleType vehicleType = VehicleType.valueOf(scanner.nextLine());
        displayVehicles(dealership.getVehicleByType(vehicleType));
    }

    private void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest(Scanner scanner) {
        System.out.print("Enter vin: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter year: ");
        int year1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maker: ");
        String makeVehicle = scanner.nextLine();
        System.out.print("Enter model: ");
        String modelVehicle = scanner.nextLine();
        System.out.print("Enter type: ");
        VehicleType vehicleType1 = VehicleType.valueOf(scanner.nextLine());
        System.out.print("Enter color: ");
        String colorVehicle = scanner.nextLine();
        System.out.print("Enter odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        double priceVehicle = Double.parseDouble(scanner.nextLine());
        dealership.addVehicle(new Vehicle(vin, year1, makeVehicle,
                modelVehicle, vehicleType1, colorVehicle, odometer, priceVehicle));
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealerShip(dealership , 1);
    }

    private void processRemoveVehicleRequest(Scanner scanner) {
        System.out.print("Enter vin: ");
        int vin0 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter year: ");
        int year0 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maker: ");
        String makeVehicle0 = scanner.nextLine();
        System.out.print("Enter model: ");
        String modelVehicle0 = scanner.nextLine();
        System.out.print("Enter type: ");
        VehicleType vehicleType0 = VehicleType.valueOf(scanner.nextLine());
        System.out.print("Enter color: ");
        String colorVehicle0 = scanner.nextLine();
        System.out.print("Enter odometer: ");
        int odometer0 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        double priceVehicle0 = Double.parseDouble(scanner.nextLine());
        dealership.removeVehicle(new Vehicle(vin0, year0, makeVehicle0,
                modelVehicle0, vehicleType0, colorVehicle0, odometer0, priceVehicle0));
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        processGetAllVehiclesRequest();
        dealershipFileManager.saveDealerShip(dealership , 2);
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

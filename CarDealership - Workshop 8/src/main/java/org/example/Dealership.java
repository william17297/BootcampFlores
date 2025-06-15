package org.example;

import java.time.LocalDate;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private VehicleDao vehicleDao;
    private SalesDao salesDao;
    private LeaseDao leaseDao;

    public Dealership(String [] Args){
        this.vehicleDao = new VehicleDao("jdbc:mysql://localhost:3306/dealership" , Args[0] ,Args[1]);
        this.salesDao = new SalesDao("jdbc:mysql://localhost:3306/dealership" , Args[0] ,Args[1]);
        this.leaseDao = new LeaseDao("jdbc:mysql://localhost:3306/dealership" , Args[0] ,Args[1]);
    }
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehicleByPrice(double min, double max) {
        return vehicleDao.getByPriceRange(min , max);
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model) {
        return vehicleDao.getByMakeAndModel(make , model);
    }

    public List<Vehicle> getVehicleByYear(int min, int max) {
        return vehicleDao.getByYear(min , max);
    }

    public List<Vehicle> getVehicleByColor(String color) {
        return vehicleDao.getByColor(color);
    }

    public List<Vehicle> getVehicleByMileage(int min, int max) {
        return vehicleDao.getByMileage(min , max);
    }

    public List<Vehicle> getVehicleByType(VehicleType vehicleType) {
        return vehicleDao.getByVehicleType(vehicleType.toString());
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAll();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleDao.create(vehicle);

    }

    public void removeVehicle(int vin) {
        vehicleDao.delete(vin);
    }

    public void sellOrLeaseVehicle(int vin, String name, String email, int choice , boolean wantsFinance) {
        for(int i = 0; i < getAllVehicles().size(); i++) {
            Vehicle vehicle = getAllVehicles().get(i);
            if (vin == vehicle.getVin()) {
                if(choice == 1) {
                    SalesContract salesContract;
                    if (wantsFinance) {
                        salesContract = new SalesContract(String.valueOf(LocalDate.now()), name, email, vehicle, true);
                        salesDao.create(salesContract);
                        System.out.println("\n\nContract added.");
                    } else {
                        salesContract = new SalesContract(String.valueOf(LocalDate.now()), name, email, vehicle, false);
                        salesDao.create(salesContract);
                        System.out.println("\n\nContract added.");
                    }
                    salesDao.update(vin);
                    break;
                }
                else if(choice == 2){

                    int age =  LocalDate.now().getYear() - vehicle.getYear();
                    if(age <= 3) {
                        leaseDao.create(new LeaseContract(String.valueOf(LocalDate.now()), name, email, vehicle));
                        System.out.println("\n\nContract added.");
                        leaseDao.update(vin);
                    }
                    else{
                        System.out.println("You can not lease that vehicle.");
                    }

                    break;
                }

            }
            else if( vin != vehicle.getVin() && i + 1 == getAllVehicles().size()){
                System.out.println("No vehicle registered under that vin.");
            }
        }
    }

}

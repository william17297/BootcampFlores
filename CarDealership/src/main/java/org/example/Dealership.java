package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
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

    public List<Vehicle> getVehicleByPrice(double min , double max){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if((vehicle.getPrice() >= min) &&   (vehicle.getPrice() <= max)){
                vehicles.add(inventory.get(i));
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehicleByMakeModel(String make , String model){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if(make.equalsIgnoreCase(vehicle.getMake()) && model.equalsIgnoreCase(vehicle.getModel())){
                vehicles.add(inventory.get(i));
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehicleByYear(double min , double max){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if((vehicle.getYear() >= min) && (vehicle.getYear() <= max)){
                vehicles.add(inventory.get(i));
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehicleByColor(String color){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if(color.equalsIgnoreCase(vehicle.getColor())){
                vehicles.add(inventory.get(i));
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehicleByMileage(double min , double max){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if((vehicle.getOdometer() >= min) && (vehicle.getOdometer() <= max)){
                vehicles.add(inventory.get(i));
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehicleByType(VehicleType vehicleType){
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            Vehicle vehicle = inventory.get(i);
            if(vehicleType.equals(vehicle.getVehicleType())){
                vehicles.add(inventory.get(i));
            }
        }
        return vehicles;
    }

    public List<Vehicle> getAllVehicles(){
        return inventory;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);

    }

    public void removeVehicle(Vehicle vehicle){
        for(int i = 0; i < inventory.size(); i++){

            if(inventory.get(i).toString().equalsIgnoreCase(vehicle.toString())){
                inventory.remove(inventory.get(i));
            }
        }
    }
}

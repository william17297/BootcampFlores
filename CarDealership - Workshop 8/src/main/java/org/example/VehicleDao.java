package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final String connectionString;
    private final String userName;
    private final String password;

    public VehicleDao(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }

    public List<Vehicle> getByPriceRange(double priceMin, double priceMax) {
        List<Vehicle> list = new ArrayList<>();
            String query = "SELECT * FROM VEHICLES WHERE Price BETWEEN ? and ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, priceMin);
            stmt.setDouble(2, priceMax);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Vehicle> getByMakeAndModel(String make, String model) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE VehicleMake = ? AND VehicleName = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return list;
    }

    public List<Vehicle> getByYear(int YearMin , int YearMax) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE VehicleYear BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, YearMin);
            stmt.setInt(2, YearMax);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return list;
    }

    public List<Vehicle> getByColor(String color) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE VehicleColor = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, color);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return list;
    }

    public List<Vehicle> getByMileage(int mileageMin , int mileageMax) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE Odometer BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mileageMin);
            stmt.setInt(2, mileageMax);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return list;
    }

    public List<Vehicle> getByVehicleType(String type) {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE VehicleType = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Vehicle> getAll() {
        List<Vehicle> list = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getInt("VIN")
                            , rs.getInt("VehicleYear")
                            , rs.getString("VehicleMake"), rs.getString("VehicleName")
                            , VehicleType.valueOf(rs.getString("VehicleType"))
                            , rs.getString("VehicleColor")
                            , rs.getInt("Odometer")
                            , rs.getDouble("Price")
                    );
                    if(!rs.getBoolean("Sold") && !rs.getBoolean("Leased")) {  //Checks if sold
                        list.add(vehicle);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void delete(int vin) {
        String query = "DELETE FROM Vehicles WHERE VIN = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vin);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void create(Vehicle vehicle){
        String query = "INSERT INTO Vehicles (VIN, VehicleName, VehicleMake, VehicleType, VehicleColor, VehicleYear, Price, Sold ,Leased, Odometer) " +
                "VALUES(? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";

        try(Connection conn = DriverManager.getConnection(connectionString , userName , password);
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1 , vehicle.getVin());
            stmt.setString(2 , vehicle.getModel());
            stmt.setString(3 , vehicle.getMake());
            stmt.setString(4 , vehicle.getVehicleType().toString());
            stmt.setString(5 , vehicle.getColor());
            stmt.setInt(6 , vehicle.getYear());
            stmt.setDouble(7 , vehicle.getPrice());
            stmt.setInt(8, 0);
            stmt.setInt(9, 0);
            stmt.setDouble(10 , vehicle.getOdometer());

            stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}

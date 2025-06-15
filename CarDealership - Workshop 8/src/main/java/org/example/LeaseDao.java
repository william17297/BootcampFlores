package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private final String connectionString;
    private final String userName;
    private final String password;

    public LeaseDao(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }

    public void create(LeaseContract leaseContract){
        String query = "INSERT INTO LeaseContract ( CustomerName , CustomerEmailAddress , Date," +
                " LeaseFee , EndingValue , MonthlyPayment , TotalPrice ,VIN) VALUES( ? , ? , ? , ? , ? , ? , ? , ?)";

        try(Connection conn = DriverManager.getConnection(connectionString , userName , password);
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1 , leaseContract.getName());
            stmt.setString(2 , leaseContract.getEmail());
            stmt.setString(3 , leaseContract.getDate());
            stmt.setDouble(4 , leaseContract.getLeaseFee());
            stmt.setDouble(5 , leaseContract.getExpectedEndingValue());
            stmt.setDouble(6 , leaseContract.getMonthlyPayment());
            stmt.setDouble(7 , leaseContract.getTotalPrice());
            stmt.setInt(8 , leaseContract.getVehicle().getVin());
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void update(int vin){
        String query = "UPDATE Vehicles SET Leased = ? WHERE VIN = ?";

        try(Connection conn = DriverManager.getConnection(connectionString , userName , password);
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setBoolean(1 , true);
            stmt.setInt(2 , vin);
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}

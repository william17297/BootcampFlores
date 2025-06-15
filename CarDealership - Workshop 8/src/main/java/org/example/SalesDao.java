package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private final String connectionString;
    private final String userName;
    private final String password;

    public SalesDao(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }

    public void create(SalesContract salesContract){
        String query = "INSERT INTO SalesContract ( CustomerName , CustomerEmailAddress , Date," +
                " TaxAmount, RecordingFee , ProcessingFee , Financed , MonthlyPayment , TotalPrice ,VIN) VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";

        try(Connection conn = DriverManager.getConnection(connectionString , userName , password);
            PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setString(1 , salesContract.getName());
            stmt.setString(2 , salesContract.getEmail());
            stmt.setString(3 , salesContract.getDate());
            stmt.setDouble(4 , salesContract.getTaxAmount());
            stmt.setDouble(5 , salesContract.getRecordingFee());
            stmt.setDouble(6 , salesContract.getProcessingFee());
            stmt.setBoolean(7 , salesContract.wantsFinance());
            stmt.setDouble(8 , salesContract.getMonthlyPayment());
            stmt.setDouble(9 , salesContract.getTotalPrice());
            stmt.setInt(10 , salesContract.getVehicle().getVin());
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void update(int vin){
        String query = "UPDATE Vehicles SET Sold = ? WHERE VIN = ?";
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

package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public void saveContract(Contract contract){
        String filePath = "src/main/resources/contracts.csv";
        File file = new File(filePath);

        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;

            FileWriter writer = new FileWriter(file , true);

            if (isEmpty) {
                writer.write("---------Contracts---------\n");
            }

            if(contract instanceof SalesContract) {
                writer.write("SALE|" + contract.getDate() + "|" + contract.getName() + "|" + contract.getEmail() + "\n");
                writer.write(contract.getVehicle().toString() + "\n");
                writer.write( String.format("%.2f", ((SalesContract) contract).getTaxAmount()) + "|" + String.format("%.2f", ((SalesContract) contract).getRecordingFee()) + "|"
                        + String.format("%.2f", ((SalesContract) contract).getProcessingFee()) + "|" + ((SalesContract) contract).WantsFinance() + "|" + String.format("%.2f", contract.getMonthlyPayment()) + "\n");
                writer.close();
            }
            else if(contract instanceof LeaseContract) {
                writer.write("LEASE|" + contract.getDate() + "|" + contract.getName() + "|" + contract.getEmail() + "\n");
                writer.write(contract.getVehicle().toString() + "\n");
                writer.write( String.format("%.2f", ((LeaseContract) contract).getExpectedEndingValue())+ "|" +  String.format("%.2f", contract.getMonthlyPayment()) + "|"
                        + String.format("%.2f", ((LeaseContract) contract).getLeaseFee()) + "\n");
                writer.close();
                }
        }
        catch(IOException e) {
            System.out.println("Something went wrong while saving the contract.");
            e.printStackTrace();
        }
    }

}

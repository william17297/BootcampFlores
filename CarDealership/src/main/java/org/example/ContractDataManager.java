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
                writer.write( ((SalesContract) contract).getTaxAmount() + "|" + ((SalesContract) contract).getRecordingFee() + "|"
                        + ((SalesContract) contract).getProcessingFee() + "|" + ((SalesContract) contract).WantsFinance() + "|" + contract.getMonthlyPayment() + "\n");
                writer.close();
            }
            else if(contract instanceof LeaseContract) {
                writer.write("LEASE|" + contract.getDate() + "|" + contract.getName() + "|" + contract.getEmail() + "\n");
                writer.write(contract.getVehicle().toString() + "\n");
                writer.write( ((LeaseContract) contract).getExpectedEndingValue()+ "|" +  contract.getMonthlyPayment() + "|"
                        + ((LeaseContract) contract).getLeaseFee() + "\n");
                writer.close();
                }
        }
        catch(IOException e) {
            System.out.println("Something went wrong while saving the contract.");
            e.printStackTrace();
        }
    }

}

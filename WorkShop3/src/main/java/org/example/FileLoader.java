package org.example;

import java.io.BufferedReader;
import  java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public static List<Product> readFile() { // <--- This is called a method signuture
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();  //skip the first line like when we do .nextline();

            String input;
            List<Product> productList = new ArrayList<>();

            while((input = bufferedReader.readLine()) != null){
              String[] row =  input.split("\\|"); //Split gives you an array of strings
                //index 0 is the SKU  , index 1 is the ProductName , index 2 is the price , and index 3 is department
                String sku = row[0];
                String productName = row[1];
                double price = Double.parseDouble(row[2]);
                String department = row[3];
                Product product = new Product(sku , productName, price, department);
                productList.add(product);
            }
            bufferedReader.close();
            return productList;

        } catch (IOException ex) {
            System.out.println("Failed to load csv file.");
            return new ArrayList<>();

        }
    }
}

//Use FileReader class and Buffer Reader to load the file
//Checked Exception coming up

//Loop through the file line by line
//skip the first line of the file because its the header

//take each line, and split it on the |

//we need to convert data as needed
//price will need conversion to a double

//create a product object to hold the data.

//put it in the product in a list
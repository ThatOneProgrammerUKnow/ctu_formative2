/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.formative2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author kobus
 */
public class MainApp {

    public static void main(String[] args) {
        // |=========================| Initiating objects |=========================|
        // |===============| Configuration |===============|
        char delimeter = '#';
        String vehiclesFileName = "vehicles.txt";
        
        // |===============| Configuration |===============|
        
        
        // |===============| Configuration |===============|
        System.out.println("=== Populating class objects ===");
        File file = new File(vehiclesFileName);
        
        // |===============| First file read - counting objects |===============|
        System.out.println("System: Counting vehicles...");
        int totalFields = 0;
        int totalObjects = 0;
        try {
            Scanner scanner = new Scanner(file);
            
            // Local variable decleration - Before first loop through text file
            int currentField = 0;
            
            
            // First time looping through text file
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                
                // Counting objects
                if (data.indexOf(delimeter) != -1 && scanner.hasNextLine()){ // If there is a delimeter and a next line: count as object
                    totalObjects++;
                    if (currentField > totalFields) { // If current amount of fields exceeds the current total amount of fields, update total fields
                        totalFields = currentField; 
                    } 
                    currentField = 0;
                } // Count fields
                else {
                    currentField++;
                }
            }
            scanner.close();
            System.out.printf("System: Succesfully counted %d objects and %d fields\n", totalObjects, totalFields);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            e.printStackTrace();
        }
        
        // Constructing array
        Vehicle[] vehicles = new Vehicle[totalObjects];
        int dels = 0;
        // Second file read: Populating arrays
        System.out.println("Constructing objects...\n");
        try {
            Scanner scanner = new Scanner(file);
            // |==| Second time looping through text file |==|
            // Variable decleration: row and col
            int row = 0;
            int col = 0;
            
            // Construct array object to save all the attributes of each object, before passing it into the class
            String[] attributes = new String[totalFields];
            // Looping through every object counted
            while (row < totalObjects  && scanner.hasNextLine()) {
                
                
                String data = scanner.nextLine(); 
                
                // If there is a delimeter AND col is 0 - There was no fields in the previous "section", skip it and don't add up row
                if (data.indexOf(delimeter) != -1 && col == 0){
                    data = scanner.nextLine();
                    System.out.printf("There is a delimeter and col is %d\n", col);
                }
                
                // If there is a delimeter or if it is the last line in the file - End of an object. 
                // - reset col; - construct object; - reset attributes array; - Increase row;
                if (data.indexOf(delimeter) != -1){
                    System.out.println("There is a delimeter and it is the end of the object\n");
                    // Reset col
                    col=0;
                    
                    // construct object
                    vehicles[row] = new Vehicle(attributes[0], attributes[1], Short.parseShort(attributes[2]));
                    
                    // Reset attributes array
                    for (int i = 0; i<attributes.length; i++){
                        attributes[i] = "";
                    }
                    
                    // Increase row
                    row++;
                } else { // If there is not a delimeter | - Save data to attributes array; - Increase col;
                    System.out.printf("There is no delimeter and col is %d\n", col);
                    attributes[col] = data;
                    col++;
                }
               
            } // |--------------------> End of outer while loop
            scanner.close();
            System.out.println("System: Succsesfully constructed vehicles from text file!\n");
 
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            e.printStackTrace();
        }// |----------------------------------------> End of try and catch
        for (int i=0; i<vehicles.length; i++){
            System.out.printf("%-15s | %-15s | %-8d\n",vehicles[i].vin, vehicles[i].model, vehicles[i].year);
        }
        
        
        // Mock data
        short year = 2026;
        Vehicle vehicle1 = new Vehicle("BMW1234567890123", "BMW x5", year);
        
        // Initiating objects
        Scanner input = new Scanner(System.in);
        
        // Main app
        System.out.println("=== BMW Digital Service Appointment System ===");
        String vin = input.nextLine();
        
        
        input.close();
    }
}

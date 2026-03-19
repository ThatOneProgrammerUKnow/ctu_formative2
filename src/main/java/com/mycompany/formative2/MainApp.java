/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.formative2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author kobus
 */
public class MainApp {
// --->>> Comment templates <<<---
// |=================================================================| 40chars |=================================================================|
// |==================================================| 30chars |==================================================|
// |=========================| 15chars |=========================|
// |===============| 10chars |===============|
// |=====| 5chars |=====|

// ===== Copy 
    
    
    public static void main(String[] args) {
        // |==================================================| Retrieving and constructing objects |==================================================|
        // |===============| Configuration |===============|
        char delimeter = '#';
        String vehiclesFileName = "vehicles.txt";
        
        
        
        // |===============| Configuration |===============|
        System.out.println("-> Populating class objects");
        File file = new File(vehiclesFileName);
        
        // |===============| First file read - counting objects |===============|
        System.out.println("-> System: Counting vehicles...");
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
            System.out.println("-> Error: File not found");
            e.printStackTrace();
        }
        
        // Constructing array
        Vehicle[] vehicles = new Vehicle[totalObjects];
        int dels = 0;
        // |===============| Second file read - constructing objects |===============|
        System.out.println("-> Constructing objects...\n");
        
        try {
            Scanner scanner = new Scanner(file);
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
                }
                
                // If there is a delimeter or if it is the last line in the file - End of an object. 
                // - reset col; - construct object; - reset attributes array; - Increase row;
                if (data.indexOf(delimeter) != -1){
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
                    attributes[col] = data;
                    col++;
                }
               
            } // |--------------------> End of outer while loop
            scanner.close();
            System.out.println("System: Succsesfully constructed vehicles from text file!\n");
 
        } catch (FileNotFoundException e) {
            System.out.println("-> Error: File not found");
            e.printStackTrace();
        }// |----------------------------------------> End of try and catch
        
// Display vehicle objects
//        for (int i=0; i<vehicles.length; i++){
//            System.out.printf("%-15s | %-15s | %-8d\n",vehicles[i].vin, vehicles[i].model, vehicles[i].year);
//        }
        
        
// |==================================================| Dealing with user input |==================================================|
        // Initiating objects
        Scanner input = new Scanner(System.in);
        
// |=========================| Implementing VIN Lookup |=========================|
        // |=====| Getting VIN |=====|
        System.out.println("===== BMW Digital Service Appointment System =====");
        System.out.println("Please enter the Vehicle Identification number to retrieve verhicle information: ");
        String lookupVin = input.nextLine();
        
        // |=====| Looking up |=====|
        System.out.println("-> Searching for vehicle...");
        int lookupVehicleId = -1;
        int i = 0;
        while (lookupVehicleId == -1 && i < vehicles.length){ // while lookupVehicle is not changes and there are still objects left o be searched   
            System.out.printf("-> Searching vehicle %d with VIN:'%s'\n", i, vehicles[i].vin);
            if (vehicles[i].vin.equals(lookupVin)){ // If current vin = user input vin : update lookup id
                lookupVehicleId = i;
                System.out.println("-> Succsesfully found vehicle " + i);
            }
            
            i++;
        }
        
        // |=====| Validation |=====|
        if (lookupVehicleId != -1) { // If the vin was found
            System.out.println("\nVehicle details:");
            System.out.printf("Vehicle Identification Number: %s\nModel: %s\nYear: %d\n", 
                    vehicles[lookupVehicleId].vin, vehicles[lookupVehicleId].model, vehicles[lookupVehicleId].year);
        } else{
            System.out.println("Vehicle not found.");
        }

        // |=====| New information obtained |=====|
        // Vehicle id
        // vehicles[lookupVehicleId].\attribute
// |=========================| Book service appoiintment |=========================|
        if (lookupVehicleId != -1){
            System.out.println("=== Book service appointment ===");
            // |=====| Date format |=====|
            String dateFormat = "(DD/MM/YYYY)";
            // |=====| Obtaining appointment information |=====|
            // Get customer name Customer: Type String
            System.out.println("Enter Customer Name: ");
            String customer = input.nextLine();

            // Get customer contact number: Type String
            System.out.println("Enter Contact Number");
            String contactNum = input.nextLine();

            // -->> Get appointment data: Type date <<--
            LocalDate appointmentDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (appointmentDate == null){
                System.out.println("Enter Appointment Date " + dateFormat);
                String userInput = input.nextLine();

                try{ // Trying to parse string to date object
                    appointmentDate = LocalDate.parse(userInput, formatter);
                    System.out.println("Succesfully parsed date");
                } catch (DateTimeParseException e) { // Print error to the screen: ask user to enter correct 
                    System.err.println("Invalid date format or date: " + userInput);
                    System.err.println("Please use correct format and enter a valid date");
                }
            // --->>> Service appointment booked
            // |===============| Confirm service appointment  |===============|
            System.out.println("\nAppointment confirmation\n----------------------");
            System.out.println("Vehicle details:");
            System.out.printf("VIN: %s\nModel: %s\nYear: %s\n\n", 
                    vehicles[lookupVehicleId].vin, vehicles[lookupVehicleId].model, vehicles[lookupVehicleId].year);
            System.out.println("Appointment details:");
            System.out.printf("Customer Name: %s\nCustomer Contact Number: %s\nAppointment Date %s%n\n\n", customer, contactNum, appointmentDate);
            
            System.out.println("Confirm appointment: (Y/N)");
            
            // Confirm appointment
            userInput = input.nextLine();
            userInput = userInput.toUpperCase();
            
            if (userInput.indexOf("Y") != -1) { // There is a 'Y' in the user input and the appointment is confirmed
                Appointment appointmentObject = new Appointment(vehicles[lookupVehicleId].vin, appointmentDate);
                Customer customerObject = new Customer(customer, contactNum);
                System.out.println("Appointment succesfully booked");
                
            } else { // There is not a 'y' in the user input and the appointment is not confirmed
                System.out.println("Appointment succesfully canceled");
            }
            
            
        }   
        }
        
        
       
        
        

        input.close(); // |----->>> Closing input
    }
}

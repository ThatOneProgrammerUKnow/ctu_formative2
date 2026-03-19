/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.formative2;
/**
 *
 * @author kobus
 */


public class Vehicle {
    private String vin;
    private String model;
    private short year;
    
    static int objectCount = 0;
    
    public Vehicle(String vin, String model, short year){
        this.vin = vin;
        this.model=model;
        this.year=year;
        
        objectCount++;
    }
    
       @Override
       public String toString(){
           return  
                "VIN: " + this.vin+
                "Model: " + this.model +
                "Year: " + this.year;
           
       }
       
       // Return the total amount of objects
       public static int totalObjects(){
           return objectCount;
       }
       
       public String getVin(){
           return this.vin;
       }
       
       public String getModel(){
           return this.model;
       }
       
       public short getYear(){
           return year;
       }
       
       
    
}


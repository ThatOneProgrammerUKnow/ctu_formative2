/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.formative2;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author kobus
 */
public class Appointment {
    private int id;
    private String vin;
    private LocalDate date;
    private LocalTime time;
    
    // Constructor method
    public Appointment(int id, String vin, LocalDate date, LocalTime time) {
        this.id = id;
        this.vin = vin;
        this.date = date;
        this.time = time;
    }
    
    // Overriding toString method
    @Override
    public String toString(){
        // Formatting date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.date.format(formatter);
        formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = this.time.format(formatter);
        
        // Return
        String result = "Appointment is schedueled for " + formattedDate + " at " + formattedTime + " for vehicle with VIN: " + this.vin;
        return result;
    }
}

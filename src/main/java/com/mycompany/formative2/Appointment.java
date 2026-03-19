/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.formative2;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author kobus
 */
public class Appointment {
    private int id;
    private String vin;
    private LocalDate date;
    
    static private int totalAppointments = -1;
    
    // Constructor method
    public Appointment(String vin, LocalDate date) {
        totalAppointments++;
        
        this.id = totalAppointments;
        this.vin = vin;
        this.date = date;

    }
    
    // Overriding toString method
    @Override
    public String toString(){
        // Formatting date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.date.format(formatter);
        
        // Return
        String result = "Appointment is schedueled for " + formattedDate + " for vehicle with VIN: " + this.vin;
        return result;
    }
}
